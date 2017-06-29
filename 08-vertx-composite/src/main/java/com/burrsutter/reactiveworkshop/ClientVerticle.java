package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;


/**
 * Created by burr on 2/1/17.
 */
public class ClientVerticle extends AbstractVerticle {
    static final String HOST = "http://localhost:8081";

    public void start() {
        System.out.println("Client Running");
        HttpClient client = vertx.createHttpClient(new HttpClientOptions()
                .setDefaultHost("localhost")
                .setMaxPoolSize(2)
                .setDefaultPort(8081)
        );

        client.get("/users", response -> {
            response.bodyHandler( body -> {
                // System.out.println("Received: " + body);
                JsonArray users = body.toJsonArray();
                int users_size = users.size();
                for (int i = 0; i < users_size; i++) {
                    JsonObject user = users.getJsonObject(i);
                    String followers_url = user.getString("followers_url");
                    String userId = user.getString("login");
                    System.out.print(userId + " : ");
                    System.out.println(followers_url);
                    client.get(followers_url, follower_response -> {
                        follower_response.bodyHandler( follower_body -> {
                            // System.out.println(follower_body.toString());
                            JsonArray followers = follower_body.toJsonArray();
                            int followers_size = followers.size();
                            for (int fi = 0; fi < followers_size; fi++) {
                                JsonObject follower = followers.getJsonObject(fi);

                                String specific_user_url = HOST + "/users/" + follower.getString("login");
                                // System.out.println("**" + specific_user_url + "**");
                                client.get(specific_user_url, user_response -> {
                                    user_response.bodyHandler( user_body -> {
                                        System.out.println("user: " + userId + " has follower: " + user_body.toJsonObject().getString("name"));
                                    });
                                }).setTimeout(2000)
                                        .putHeader("Content-Type", "application/json")
                                        .putHeader("Accept", "application/json")
                                        .end();
                            } // foreach follower
                        });
                    }).setTimeout(2000)
                            .putHeader("Content-Type", "application/json")
                            .putHeader("Accept", "application/json")
                            .end();
                } // foreach user
            });
        }).setTimeout(2000)
                .putHeader("Content-Type", "application/json")
                .putHeader("Accept", "application/json")
                .end();

    } // start()
}
