def eb = vertx.eventBus()

eb.consumer("my-feed", { message ->
  println("MyGroovyConsumer.groovy received: ${message.body()}")
})

