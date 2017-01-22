package com.burrsutter.reactiveworkshop;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;

import io.vertx.example.util.Runner;

public class ServerGET extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runExample(ServerGET.class);
    }

  @Override
  public void start() throws Exception {
      HttpServer server = vertx.createHttpServer();
      server.requestStream().toObservable().subscribe(req -> {
        req.response()
          .putHeader("content-type", "text/html")
          .end("You GET it");
      });
      server.listen(8080);
  } // start
}
