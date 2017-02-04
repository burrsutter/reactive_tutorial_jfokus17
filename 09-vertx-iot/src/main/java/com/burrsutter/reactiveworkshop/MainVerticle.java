package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;

/**
 * Created by burr on 1/28/17.
 */
public class MainVerticle extends AbstractVerticle {
    public void start() throws Exception {
        vertx.deployVerticle(MQTTServer.class.getName());
        vertx.deployVerticle(WebServer.class.getName());
    }
}
