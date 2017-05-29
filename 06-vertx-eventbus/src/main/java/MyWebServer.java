import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEventType;

public class MyWebServer extends AbstractVerticle {
  @Override
  public void start() throws Exception {

    Router router = Router.router(vertx);
    
    BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("my-feed"));
    router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options, event -> {
      
      if (event.type() == BridgeEventType.SOCKET_CREATED) {
        System.out.println("A socket was created");
      }
      event.complete(true);
    }));

    // static assets in local folder "webroot"
    // and available at http://localhost:8080/
    router.route().handler(StaticHandler.create());

    vertx.createHttpServer()        
        .requestHandler(router::accept)
        // modified this to 80 for running as public web app
        .listen(80);

    System.out.println("Everyting is non-blocking Async :-)");

    vertx.setPeriodic(1000, t -> vertx.eventBus().publish("my-feed", "Server Now " + new java.util.Date()));

  } // start
}