package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Created by burr on 1/22/17.
 * Docs:
 * https://docs.google.com/document/d/1UlnyueeYoeAXFkhN-1ePRTxKyFWAGNatZfsTepDLU6Q/edit#
 */
public class MainVerticle extends AbstractVerticle {

    public void start() throws Exception {
        System.out.println("Hello");

        Router router = Router.router(vertx);

        router.get("/").handler(request -> {
            request.response().end("Try \n/hello/yourname \n"
                    + "/goodbye \n"
                    + "/blowup \n"
                    + new java.util.Date());
        });

        router.get("/hello/:name").handler(request ->
                request.response().end("Hello " + request.pathParam("name") + " " + new java.util.Date())
        );

        router.get("/goodbye").handler(request -> request.response().end("goodbye"));

        router.get("/blowup").handler(request -> {
            throw new RuntimeException("Damn It");
        });

        // serves static files from resources/webroot
        // router.route().handler(StaticHandler.create());

        router.route().failureHandler(ErrorHandler.create());

        /*
        Normally for local development you would use 8080
        Here I am using 80 for running as a public webapp
        */
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(80);


  }
}
