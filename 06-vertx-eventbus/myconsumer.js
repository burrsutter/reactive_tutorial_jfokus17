var eb = vertx.eventBus();

eb.consumer("my-feed", function (message) {
  console.log("myconsumer.js received: " + message.body());
});


