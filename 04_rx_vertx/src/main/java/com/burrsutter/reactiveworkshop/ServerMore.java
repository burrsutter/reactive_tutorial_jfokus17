package com.burrsutter.reactiveworkshop;

import io.vertx.example.util.Runner;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

/**
 * Created by burr on 1/21/17.
 */
public class ServerMore extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runExample(ServerMore.class);
    }

    @Override
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(
                request -> {
                    System.out.println(request.method() + "" +
                            request.uri());
                    request.response().putHeader("content-type","text/plain")
                            .end("Back at You!");
                },
                error -> {
                    System.out.println("Error: " + error);
                },
                () -> {
                   System.out.println("Completed");
                }
        );
        server.listen(8080);

    } // start
}
