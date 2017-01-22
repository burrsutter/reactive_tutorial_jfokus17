package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.Runner;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
/**
 * Created by burr on 1/21/17.
 */
public class ServerNoRx1 extends AbstractVerticle {
    /*
    public static void main(String[] args) {
        Runner.runExample(ServerNoRx1.class);
    }
    */
    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.get("/").handler(this::getRoot);
        router.put("/").handler(this::putRoot);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    } //

    private void putRoot(RoutingContext routingContext) {
        System.out.println(routingContext.request().method());
        routingContext.response().end("I PUT");
    }

    private void getRoot(RoutingContext routingContext) {
        System.out.println(routingContext.request().method());
        routingContext.response().end("Wassup4");
    }


}
