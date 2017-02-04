package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

/**
 * Created by burr on 1/28/17.
 */
public class WebServer extends AbstractVerticle {
    final String TISENSOR2650 = "ti_cc2650";
    final String TISENSOR2451 = "ti_cc2541";
    final String LBBSENSOR = "lbb_sense";

    public void start(){
        Router router = Router.router(vertx);

        BridgeOptions options = new BridgeOptions()
                .addOutboundPermitted(new PermittedOptions().setAddress(TISENSOR2451))
                .addOutboundPermitted(new PermittedOptions().setAddress(TISENSOR2650))
                .addOutboundPermitted(new PermittedOptions().setAddress(LBBSENSOR));
        router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {

            if (event.type() == BridgeEventType.SOCKET_CREATED) {
                System.out.println("A socket was created");
            }
            event.complete(true);
        }));

        router.route().handler(StaticHandler.create());

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        // for testing EventBus
        // vertx.setPeriodic(1000, t -> vertx.eventBus().publish(TISENSOR, "{\"sensorid\":\"ti_cc2650\", \"temp\":26.0, \"time\":1485389121978}"));

    }
}
