# Purpose
To demonstrate the Vert.x Event Bus using several local JVMs at part of a cluster.  Also extends the Event Bus to the browser in MyWebServer.java

Requires the 'vertx' command line tool
http://vertx.io/download/
Add "bin" your PATH
Tip: On Mac OSX, add a file to /etc/paths.d called "vertx" and containing
"/Users/burr/tools/vert.x-3.3.3/bin"
or wherever you unzipped your Vert.x distribution

Terminal Window 1:
vertx run MyPublisher.java -cluster

Terminal Window 2:
vertx run MyConsumer.java -cluster

Note: cluster.xml in the root folder, needed to make life easier on Mac OSX

High Availability (HA)
In 3 different terminal windows:
vertx run MyPublisher.java -ha
vertx run MyConsumer.java -ha
vertx run myconsumer.js -ha

In a 4th terminal window:
jps
find the process id of a Launcher
kill -9 pid

and one of the remaining JVMs/nodes will start running the killed Verticle
