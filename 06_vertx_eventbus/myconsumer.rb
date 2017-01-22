eb = $vertx.event_bus()

eb.consumer("my-feed") { |message|
  puts "myconsumer.rb received: #{message.body()}"
}
