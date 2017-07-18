vertx.createHttpServer().requestHandler(function(req) {
  req.response().end("Hello Vert.x JavaScript! " + Date.now());
}).listen(8080);