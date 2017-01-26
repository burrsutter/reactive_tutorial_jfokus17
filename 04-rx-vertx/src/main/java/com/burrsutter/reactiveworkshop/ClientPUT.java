package com.burrsutter.reactiveworkshop;


import io.vertx.example.util.Runner;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpClient;

/**
 * Created by burr on 1/21/17.
 */
public class ClientPUT extends AbstractVerticle {
    public static void main(String[] args) {
        Runner.runExample(ClientPUT.class);
    }

    @Override
    public void start() throws Exception {
        HttpClient client = vertx.createHttpClient();
        client.put(8080,"localhost","/", response -> {
           System.out.println("Status Code: " + response.statusCode());
            response.handler(buffer -> System.out.println(buffer.toString("UTF-8")));
        }).setChunked(true).putHeader("Content-Type","text/plain").write("Stuff IN/OUT").end();

    } // start
}
