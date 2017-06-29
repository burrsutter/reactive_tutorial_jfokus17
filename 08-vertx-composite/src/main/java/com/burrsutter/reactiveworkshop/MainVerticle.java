package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;

/**
 * Created by burr on 2/1/17.
 */
public class MainVerticle extends AbstractVerticle{
    public void start() throws Exception {
        UsersVerticle users = new UsersVerticle();
        FollowersVerticle followers = new FollowersVerticle();

        // these next two options go after the localhost users and followers endpoints
        // ClientVerticle client = new ClientVerticle();
        // RxWebClient client = new RxWebClient();


        // this option goes to localhost, hit localhost:8080 with your browser
        RxWebVerticle client = new RxWebVerticle();

        // this option goes to github, hit localhost:8080 with your browser
        // RxWebVerticleGitHub client = new RxWebVerticleGitHub();


        vertx.deployVerticle(users, x -> {
            vertx.deployVerticle(followers, y -> {
                vertx.deployVerticle(client);
            });
        });
    }
}
