package com.burrsutter.reactiveworkshop;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.client.WebClient;
import io.vertx.rxjava.ext.web.client.HttpResponse;
import rx.Single;
import io.vertx.rxjava.core.buffer.Buffer;

/**
 * Created by burr on 1/24/17.
 */
public class NewRxWebClient extends AbstractVerticle {


    public void start() throws Exception {
        System.out.println("Stuff");
        WebClient rxWebClient = WebClient.create(vertx);

        /*
           Async Request/Response
        */
        rxWebClient
                .get(8080, "localhost", "/api/nap")
                .timeout(1000)
                .send(ar -> {
                    System.out.println("What are YOU: " + ar.getClass().getName());
                    if (ar.succeeded()) {
                        // Obtain response
                        HttpResponse<Buffer> response = ar.result();

                        System.out.println("Status: " + response.statusCode());
                        System.out.println("Received: " + response.bodyAsString());
                    } else {
                        System.out.println("Something went wrong " + ar.cause().getMessage());
                    }
                });

        /*
           Single Response
        */
        Single<HttpResponse<Buffer>> single = rxWebClient
                .get(8080, "localhost", "/api/goodbye")
                .rxSend();

        // Send a request upon subscription of the Single
        single.subscribe(response -> {
            System.out.println("Status: " + response.statusCode());
            System.out.println("Received: " + response.bodyAsString());
        }, error -> {
            System.out.println("Something went wrong " + error.getMessage());
        }
        );

    } // start
}
