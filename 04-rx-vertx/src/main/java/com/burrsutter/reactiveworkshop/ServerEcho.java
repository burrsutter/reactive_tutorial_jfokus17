package com.burrsutter.reactiveworkshop;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.core.http.HttpServerResponse;

public class ServerEcho extends AbstractVerticle {
  @Override
  public void start() throws Exception {
     HttpServer server = vertx.createHttpServer();
     server.requestStream().toObservable().subscribe(request -> {
         HttpServerResponse response = request.response();
         String contentType = request.getHeader("Content-Type");
         if(contentType != null) {
            response.putHeader("Content-Type", contentType);
         }
         response.setChunked(true);
         request.toObservable().subscribe(
            response::write,
                 error -> {},
                 response::end
         ); //request.toObservable().subscribe(
     }); // server.requestStream().toObservable().subscribe(request -> {
     server.listen(8080);
  } // start
}