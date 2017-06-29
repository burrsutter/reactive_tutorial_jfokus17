package com.burrsutter.reactiveworkshop;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.client.HttpRequest;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Observable;
import rx.Single;

/**
 * Created by burr on 2/7/17.
 */
public class RxWebVerticle extends AbstractVerticle {
    WebClient rxWebClient;
    // BodyCodec<JsonArray> jsonArrayBodyCodec;

    public void start() throws Exception {
        rxWebClient = WebClient.create(vertx);

        Router router = Router.router(vertx);
        router.get("/").handler(this::getStuff);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080, ar -> {
            if(ar.succeeded()) {
                System.out.println("\n ** Main HTTP Server up on 8080 **\n");
            } else {
                System.out.println("Error: " + ar.cause());
            }
        });

    }

    private void getStuff(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        /*
            hits the /users URI on localhost:8081
            that results a JSON array of users
            then fetch user's details
            and fetch user's followers on localhost:8082 /followers/:loginid

        */
        rxWebClient.get(8081, "localhost", "/users")
                .putHeader("Accept", "application/json")
                .putHeader("User-Agent", "Vert.x Web Client")
                
                .as(BodyCodec.jsonArray())

                .rxSend()

                .map(HttpResponse::body).flatMapObservable(Observable::from).cast(JsonObject.class)

                .flatMap(user -> {

                    System.out.println("**" + user.getString("login") + "**");
                    Single<JsonObject> userDetails =
                            rxWebClient.get(8081, "localhost", "/users/" +  user.getString("login") )
                                    .putHeader("Accept", "application/json")
                                    .putHeader("User-Agent", "Vert.x Web Client")
                                    .as(BodyCodec.jsonObject())
                                    .rxSend()
                                    .map(HttpResponse::body);

                    Single<JsonArray> followers =
                            rxWebClient.get(8082, "localhost", "/followers/" +  user.getString("login") )
                                .putHeader("Accept", "application/json")
                                .putHeader("User-Agent", "Vert.x Web Client")
                                .as(BodyCodec.jsonArray())
                                .rxSend()
                                .map(HttpResponse::body);


                    return userDetails.zipWith(followers,
                            (u, f) -> new JsonObject().put("user", u).put("followers", f))
                            .toObservable();  // from Single to Observable
                })
                .collect(JsonArray::new, JsonArray::add)
                .subscribe(
                        aUserFollowers -> response.write(aUserFollowers.encodePrettily()),
                        routingContext::fail,
                        response::end);

    }


}
