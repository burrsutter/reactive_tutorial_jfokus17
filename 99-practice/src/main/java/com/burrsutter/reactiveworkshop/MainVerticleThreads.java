package com.burrsutter.reactiveworkshop;

/**
 * Created by burr on 6/30/17.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class MainVerticleThreads extends AbstractVerticle {
    public void start(Future<Void> startFuture) {
        System.out.println("MainVerticleThreads started on " + Thread.currentThread().getName());
        vertx.createHttpServer()
                .requestHandler(r -> {
                    System.out.println("*1 " + Thread.currentThread().getName());
                    vertx.eventBus().send("mychannel", "message content", reply -> {
                        if(reply.succeeded()) {
                            System.out.println("reply.result() = " + reply.result().body());
                            System.out.println("*4 " + Thread.currentThread().getName());
                        } else {
                            System.out.println(reply.cause());
                        }
                    });
                    r.response().end("<h1> Vert.x 3 application</h1>");

                })
                .listen(8080, result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
