package com.burrsutter.reactiveworkshop;

import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpClient;
import io.vertx.rxjava.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;

import io.vertx.example.util.Runner;

public class ClientGET extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runExample(ClientGET.class);
    }

    public void start() throws Exception {        
        HttpClient client = vertx.createHttpClient();
        HttpClientRequest request = 
           client.request(HttpMethod.GET, 8080, "localhost", "/");
        request.toObservable()
          .flatMap(response -> {
              if (response.statusCode() != 200) {
                throw new RuntimeException("Wrong status code " + response.statusCode());
              }
              return response.toObservable();
           })
          .subscribe(data -> System.out.println("Server content: " + data.toString("UTF-8")));
        
        request.end();  
    }
}