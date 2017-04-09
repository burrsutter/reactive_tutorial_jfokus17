# Purpose:
Demonstrates client consumers and server producers.  Starting at 06, I begin to hit Twitter to pull Followers for the supplied ids.  Typically a consumer/client will provide parameters to the producer/server.  There is also Consumer/Producer for illustrating backpressure, when a producer outpaces a consumer.

* Client01/Server01 - simple example of infinite stream based on Server01's while true loop
* Client02/Server02 - Client adds data, error and completed methods and Server demonstrates use of onNext and onCompleted
* Client03/Server03 - a failing attempt to unsubscribe, corrected in the 04 example
* Client04/Server04 - demonstrates a successful unsubscription
* Client05/Server05 - subscribeOn(Schedulers.io), [Rx is single-threaded by default](http://www.introtorx.com/Content/v1.0.10621.0/15_SchedulingAndThreading.html)
* Client06/Server06 - leverages SocialData and TwitterScreenscraper to generate a stream of Twitter Followers based on the provided list of Twitter ids
* Client07/Server07 - illustrates error handling, including a 'retry' by using the same parameters (with the bad twitter id) to go back to the same service for more results.  It runs through the list twice.
* Client08/Server08 - adds a sleep to slow things down and break to complete the stream
* Client09/Server09 - uses more recognizable Twitter ids
* Consumer/Producer - demonstrates onBackpressureDrop, when the producer outpaces the consumer.  There is also a onBackpressureBuffer but I consider it to be impractical, if your consumer is too slow, it will eventually overwhelm the buffer. What happens to a radio station even if your radio is not tuned in, does it buffer while you are offline so you can "catch up"? If you must have a real buffer, a distributed buffer, use Apache Kafka :-)
Let this run for a few moments to see the DROPPED message.


# Run:
1) Modify the pom.xml to change the java.main.class
Client0X.java maps to Server0X.java

2) mvn compile exec:java
