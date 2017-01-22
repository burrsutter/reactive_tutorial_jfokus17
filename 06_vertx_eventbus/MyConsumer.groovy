def eb = vertx.eventBus()

eb.consumer("my-feed", { message ->
  println("MyConsumer.groovy received: ${message.body()}")
})

