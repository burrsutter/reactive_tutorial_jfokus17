package com.burrsutter.reactiveworkshop;

import io.vertx.core.Vertx;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;

public class Main {
    public static void main(String[] args) {
        // Vertx vertx = Vertx.vertx();

        Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(1));

        vertx.deployVerticle(AnotherVerticleThreads.class.getName(),
                new DeploymentOptions().setInstances(1), anotherResult -> {
            if(anotherResult.succeeded()) {
                vertx.deployVerticle(MainVerticleThreads.class.getName(), new DeploymentOptions().setInstances(1), mainResult -> {
                    vertx.deployVerticle(TimerVerticleThreads.class.getName());
                });
            } else {
                System.out.println(anotherResult.cause());
            }
        });

    }  // main
} // Main