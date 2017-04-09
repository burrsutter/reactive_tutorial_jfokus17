package com.burrsutter.reactiveworkshop;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Observable;
import rx.Single;
import io.vertx.core.http.HttpClientOptions;

/**
 * Created by burr on 2/7/17.
 */
public class RxWebVerticleGitHub extends AbstractVerticle {
    WebClient rxWebClient;


    public void start() throws Exception {
        rxWebClient = WebClient.create(vertx);
        rxWebClient = WebClient.wrap(vertx.createHttpClient(new HttpClientOptions().setSsl(true)));

        Router router = Router.router(vertx);
        router.get("/").handler(this::getStuff);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

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
        rxWebClient.get(443, "api.github.com", "/users")
                .putHeader("Accept", "application/vnd.github.v3+json")
                .putHeader("User-Agent", "Vert.x Web Client")
                .putHeader("Authorization", "Basic YXBpZXhwbG9yZXI1Om15cGFzc3dvcmQ=") // replace with your user:password
                .as(BodyCodec.jsonArray())
                .rxSend()
                .map(HttpResponse::body).flatMapObservable(Observable::from).cast(JsonObject.class)
                .take(1)
                .flatMap(user -> {
                    System.out.println("**" + user.getString("login") + "**");
                    Single<JsonObject> userDetails =
                            rxWebClient.get(443, "api.github.com", "/users/" +  user.getString("login") )
                                    .putHeader("Accept", "application/vnd.github.v3+json")
                                    .putHeader("User-Agent", "Vert.x Web Client")
                                    .putHeader("Authorization", "Basic YXBpZXhwbG9yZXI1Om15cGFzc3dvcmQ=") // replace with your user:password
                                    .as(BodyCodec.jsonObject())
                                    .rxSend()
                                    .map(HttpResponse::body);

                    Single<JsonArray> followers =
                            rxWebClient.get(443, "api.github.com", "/users/" +  user.getString("login") + "/followers")
                                .putHeader("Accept", "application/vnd.github.v3+json")
                                .putHeader("User-Agent", "Vert.x Web Client")
                                .putHeader("Authorization", "Basic YXBpZXhwbG9yZXI1Om15cGFzc3dvcmQ=") // replace with your user:password
                                .as(BodyCodec.jsonArray())
                                .rxSend()
                                .map(HttpResponse::body);

                    return userDetails.zipWith(followers, (u, f) -> new JsonObject().put("user", u).put("followers", f)).toObservable();
                })
                .subscribe(
                        aUserFollowers -> response.write(aUserFollowers.encodePrettily()),
                        routingContext::fail,
                        response::end);


    }
// anVua3N0dWZmMQ==

}
