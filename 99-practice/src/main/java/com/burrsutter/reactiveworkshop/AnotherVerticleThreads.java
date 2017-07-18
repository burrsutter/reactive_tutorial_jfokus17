package com.burrsutter.reactiveworkshop;

/**
 * Created by burr on 6/30/17.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.ext.web.codec.BodyCodec;

public class AnotherVerticleThreads extends AbstractVerticle {
    public void start(Future<Void> startFuture) {
        System.out.println("AnotherVerticleThreads started on " + Thread.currentThread().getName());
        vertx.eventBus().<String>consumer("mychannel").handler(this::myhandler);
        startFuture.complete();
    }

    private void myhandler(Message<String> message) {
        String msg = message.body();

        System.out.println("*2 " + Thread.currentThread().getName());

        // go hit a web API
        WebClient client = WebClient.create(vertx, new WebClientOptions()
            .setSsl(true)
            .setUserAgent("vert-x3")
        );
        client.get(443, "api.github.com", "/users")
                .putHeader("Accept", "application/vnd.github.v3+json")
                .as(BodyCodec.jsonArray())
                .send(ar -> {
                    if(ar.succeeded()) {
                        System.out.println("*3 " + Thread.currentThread().getName());
                        message.reply("3 " + Thread.currentThread().getName() + " ");
                    } else {
                        System.out.println("webclient failed: " + ar.cause());
                        message.fail(1,"" + ar.cause());
                    }
                });


    }
}
