# Purpose
To demonstrate the Vert.x Event Bus using several local JVMs at part of a cluster.  Also extends the Event Bus to the browser in MyWebServer.java

Requires the 'vertx' command line tool
http://vertx.io/download/
Add "bin" your PATH
Tip: On Mac OSX, add a file to /etc/paths.d called "vertx" and containing
"/Users/burr/tools/vert.x-3.3.3/bin"
or wherever you unzipped your Vert.x distribution

#### Terminal Window 1:
vertx run src/main/java/MyPublisher.java -cluster

#### Terminal Window 2:
vertx run src/main/java/MyConsumer.java -cluster

Note: cluster.xml in the root folder, needed to make life easier on Mac OSX

#### Terminal Window 3:
vertx run src/main/java/MyWebServer.java -cluster

[http://localhost:8080](http://localhost:8080)

Note: I have this set to port 80 for running on as a public web server/app

#### Terminal Window 4:
vertx run myconsumer.rb -cluster

#### Terminal Window 5:
vertx run myconsumer.js -cluster

#### Terminal Window 5:
vertx run mypublisher.rb -cluster

#### Video: (https://screencast.com/t/uMZcSHtVl)

# High Availability (HA)
In 3 different terminal windows:
vertx run MyPublisher.java -ha
vertx run MyConsumer.java -ha
vertx run myconsumer.js -ha

In a 4th terminal window:
jps
find the process id of a Launcher
kill -9 pid

and one of the remaining JVMs/nodes will start running the killed Verticle
