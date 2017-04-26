package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;

/**
 * Created by burr on 2/1/17.
 */
public class MainVerticle extends AbstractVerticle{
    public void start() throws Exception {
        UsersVerticle users = new UsersVerticle();
        FollowersVerticle followers = new FollowersVerticle();
        // RxWebVerticle web = new RxWebVerticle();
        RxWebVerticleGitHub web = new RxWebVerticleGitHub();
        // ClientVerticle client = new ClientVerticle();
        // RxWebClient client = new RxWebClient();
        vertx.deployVerticle(users, webresult -> {
            vertx.deployVerticle(followers, userresult -> {
                vertx.deployVerticle(web);
            });
        });
    }
}
