import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

public class MyPublisher extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.setPeriodic(1000, id -> {
      // This handler will get called every second
      String msg = "Java Now " + new java.util.Date();
      System.out.println(msg);
      vertx.eventBus().publish("my-feed", msg);
    });

  } // start
}