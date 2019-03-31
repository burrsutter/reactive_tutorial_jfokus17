package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.core.MultiMap;


/**
 * Created by burr on 1/22/17.
 */
public class MainVerticle extends AbstractVerticle {

    public void start() throws Exception {
        

        Router router = Router.router(vertx);

        String busAddress = "paintAddressX";
        String pushAddress = "serverpush";
        

        BridgeOptions options = new BridgeOptions();
        options.addOutboundPermitted(new PermittedOptions().setAddress(busAddress));
        options.addInboundPermitted(new PermittedOptions().setAddress(busAddress));
        
        options.addOutboundPermitted(new PermittedOptions().setAddress(pushAddress));

        router.route("/eventbus/*").handler(
                SockJSHandler.create(vertx).bridge(options, event -> {
                    if(event.type() == BridgeEventType.SOCKET_CREATED) {
                        SockJSSocket socket = event.socket();
                        MultiMap mm = socket.headers();
                        System.out.print(event.type() + " ");
                        System.out.println(mm.get("User-Agent"));
        
                        System.out.println("----");
                                
                    }
                    event.complete(true);
                })
        );

        router.route("/smile/:id").handler(rc -> {             
            String id = rc.request().getParam("id");
            System.out.println("send the user a smile " + id);
            if (id != null && !id.equals("")) {
               vertx.eventBus().publish("serverpush", id);
            }
            rc.request().response().end("Sent"); 
       });

        router.route().handler(StaticHandler.create());
        router.route().failureHandler(ErrorHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(80); // Using 80 on the audience-facing server

       
        System.out.println("Finger Painting Up 2");
        
  }
}
