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
        System.out.println("Hello4");

        Router router = Router.router(vertx);

        router.get("/hello/:name").handler(request ->
                request.response().end("Hello " + request.pathParam("name") + " " + new java.util.Date())
        );

        router.get("/goodbye").handler(request -> request.response().end("goodbye2"));

        router.get("/blowup").handler(request -> {
            throw new RuntimeException("Damn It");
        });


        router.route().handler(StaticHandler.create());
        router.route().failureHandler(ErrorHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);


  }
}
