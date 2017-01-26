import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

public class MyConsumer extends AbstractVerticle {
  @Override
  public void start() throws Exception {
      vertx.eventBus().<String>consumer("my-feed").handler(this::myhandler);
  } // start
  private void myhandler(Message<String> message) {
    String msg = message.body();
    System.out.println("MyConsumer.java received: " + msg);
  }

}