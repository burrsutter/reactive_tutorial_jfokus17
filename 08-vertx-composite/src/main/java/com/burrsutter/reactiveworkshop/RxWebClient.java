package com.burrsutter.reactiveworkshop;

import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.client.HttpRequest;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.codec.BodyCodec;
import rx.Observable;
import rx.Single;

/**
 * Created by burr on 2/3/17.
 */
public class RxWebClient extends AbstractVerticle {
    WebClient rxWebClient;


    public void start() throws Exception {
        System.out.println("START");
        rxWebClient = WebClient.create(vertx);
//        rxWebClient = WebClient.wrap(vertx.createHttpClient(new HttpClientOptions().setSsl(true)));

        BodyCodec<JsonArray> jsonArrayBodyCodec = BodyCodec.create(buf -> new JsonArray(buf.toString()));

/*
        createlocalhostRequest("/users", jsonArrayBodyCodec )
                .rxSend()
                .map(HttpResponse::body).flatMapObservable(Observable::from).cast(JsonObject.class)
                .subscribe(
                        response -> {
                            System.out.println(response.getClass().getName());
                            System.out.println(response.getInteger("id"));
                        }, error -> {
                            System.out.println("ERROR: " + error);
                        });
*/


        createlocalhostRequest("/users",jsonArrayBodyCodec )
                .rxSend()
                .map(HttpResponse::body).flatMapObservable(Observable::from).cast(JsonObject.class)
                .flatMap(user -> {

                    Single<JsonObject> userDetails = createlocalhostRequest("/users/" + user.getString("login"), BodyCodec.jsonObject())
                            .rxSend()
                            .map(HttpResponse::body);

                    Single<JsonArray> followers = createlocalhostRequest("/users/" + user.getString("login") + "/followers", jsonArrayBodyCodec)
                            .rxSend()
                            .map(HttpResponse::body);

                    Single<JsonObject> followerName = createlocalhostRequest("/users/" + user.getString("login"), BodyCodec.jsonObject())
                            .rxSend()
                            .map(HttpResponse::body);

                    followerName.subscribe(f -> {
                        System.out.println("User: " + f.getString("name"));
                    });
                    return userDetails.zipWith(followers, (u, f) -> new JsonObject().put("user", u).put("followers", f)).toObservable();

                }).subscribe(results -> System.out.println(results), System.err::println);

/*
        createlocalhostRequest("/users",jsonArrayBodyCodec )
                .rxSend()
                .map(HttpResponse::body).flatMapObservable(Observable::from).cast(JsonObject.class)
                .flatMap(user -> {
                    Single<JsonObject> userDetails = createlocalhostRequest("/users/" + user.getString("login"), BodyCodec.jsonObject())
                            .rxSend()
                            .map(HttpResponse::body);

                    Single<JsonArray> followers = createlocalhostRequest("/users/" + user.getString("login") + "/followers", jsonArrayBodyCodec)
                            .rxSend()
                            .map(HttpResponse::body);

                    return userDetails.zipWith(followers, (u, f) -> new JsonObject().put("user", u).put("followers", f)).toObservable();

                }).subscribe(results -> System.out.println(results), System.err::println);
*/


/*
        createGithubSSLRequest("/users", jsonArrayBodyCodec)
                .rxSend()
                .map(HttpResponse::body).flatMapObservable(Observable::from).cast(JsonObject.class) // transforms the Single<JsonArray> into an Observable<JsonObject>
                .flatMap(user -> {

                    Single<JsonObject> userDetails = createGithubSSLRequest("/users/" + user.getString("login"), BodyCodec.jsonObject())
                            .rxSend()
                            .map(HttpResponse::body);

                    Single<JsonArray> followers = createGithubSSLRequest("/users/" + user.getString("login") + "/followers", jsonArrayBodyCodec)
                            .rxSend()
                            .map(HttpResponse::body);

                    return userDetails.zipWith(followers, (u, f) -> new JsonObject().put("user", u).put("followers", f)).toObservable();
                })
                .subscribe((x) -> System.out.println(x.encodePrettily()), System.err::println);
*/


    } // start

    private <U> HttpRequest<U> createGithubSSLRequest(String requestURI, BodyCodec<U> bodyCodec) {
        return rxWebClient.get(443, "api.github.com", requestURI)
                .putHeader("Accept", "application/vnd.github.v3+json")
                .putHeader("User-Agent", "Vert.x Web Client") // GH requires a user agent
                // .putHeader("Authorization", "Basic kdljflkdsqjfmlkjdsqfkljdsqlk") // replace with your user:password
                .as(bodyCodec);
    }
    private <U> HttpRequest<U> createlocalhostRequest(String requestURI, BodyCodec<U> bodyCodec) {
        return rxWebClient.get(8081, "localhost", requestURI)
                .putHeader("Accept", "application/json")
                .putHeader("User-Agent", "Vert.x Web Client") // many services require user agent
                .as(bodyCodec);
    }


}
