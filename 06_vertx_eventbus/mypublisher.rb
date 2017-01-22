require "date"

eb = $vertx.event_bus()

$vertx.set_periodic(1000) { |v|
  msg = "Ruby Now " + DateTime.now.strftime("%H:%M:%S")
  puts(msg)
  eb.publish("my-feed", msg)
}
