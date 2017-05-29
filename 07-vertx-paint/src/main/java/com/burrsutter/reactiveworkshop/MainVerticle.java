package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

/**
 * Created by burr on 1/22/17.
 * Docs:
 * https://docs.google.com/document/d/1UlnyueeYoeAXFkhN-1ePRTxKyFWAGNatZfsTepDLU6Q/edit#
 */
public class MainVerticle extends AbstractVerticle {

    public void start() throws Exception {
        System.out.println("Finger Painting Up");

        Router router = Router.router(vertx);

        String busAddress = "paintAddressX";
        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddress(busAddress));
        options.addInboundPermitted(new PermittedOptions().setAddress(busAddress));


        router.route("/eventbus/*").handler(
                SockJSHandler.create(vertx).bridge(options, event -> {
                    if(event.type() == BridgeEventType.SOCKET_CREATED) {
                        System.out.println("Socket Created");
                    }
                    event.complete(true);
                })
        );

        router.route().handler(StaticHandler.create());
        router.route().failureHandler(ErrorHandler.create());
        
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(80); // Using 80 on the audience-facing server


  }
}
