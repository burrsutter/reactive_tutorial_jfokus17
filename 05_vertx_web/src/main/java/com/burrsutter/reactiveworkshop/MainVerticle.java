package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Created by burr on 1/22/17.
 * Docs:
 * https://docs.google.com/document/d/1UlnyueeYoeAXFkhN-1ePRTxKyFWAGNatZfsTepDLU6Q/edit#
 */
public class MainVerticle extends AbstractVerticle {

    public void start() throws Exception {
        System.out.println("Hello2");

        Router router = Router.router(vertx);

        router.get("/hello/:name").handler(rc ->
                rc.response().end("Hello " + rc.pathParam("name") + " " + new java.util.Date())
        );
        router.get("/goodbye").handler(rc -> rc.response().end("goodbye"));

        router.route().handler(StaticHandler.create());


        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        System.out.println("path" + new java.io.File("").getCanonicalPath());
  }
}
