package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.client.WebClient;

/**
 * Created by burr on 7/1/17.
 */
public class TimerVerticleThreads extends AbstractVerticle {
    public void start(Future<Void> startFuture) {
        vertx.setPeriodic(1000, timerHandler -> {
            WebClient client = WebClient.create(vertx);
            System.out.println("timerVerticle " + Thread.currentThread().getName());

            client.get(8080, "localhost",  "/")
                  .send(webClientHandler -> {
                    if(webClientHandler.succeeded()) {
                        System.out.println("" + webClientHandler.result() + " on " + Thread.currentThread().getName());
                    } else {
                        System.out.println("webclient failed: " + webClientHandler.cause());
                    }
            }); // send
        }); // setPeriodic
        startFuture.complete();
    }
}
