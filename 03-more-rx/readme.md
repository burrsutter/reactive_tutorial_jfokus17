# Purpose:
Demonstrates client consumers and server producers

* Client01/Server01 - simple example of infinite stream based on Server01's while true loop
* Client02/Server02 - Client adds data, error and completed methods and Server demonstrates use of onNext and onCompleted
* Client03/Server03 - a failing attempt to unsubscribe, corrected in the 04 example
* Client04/Server04 - demonstrates a successful unsubscription
* Client05/Server05 - subscribeOn(Schedulers.io), [Rx is single-threaded by default](http://www.introtorx.com/Content/v1.0.10621.0/15_SchedulingAndThreading.html)
* Client06/Server06 - leverages SocialData and TwitterScreenscraper to generate a stream of Twitter Followers based on the provided list of Twitter ids
* Client07/Server07 - illustrates error handling, including a 'retry' by using the same parameters (with the bad twitter id) to go back to the same service for more results.  It runs through the list twice.
* Client08/Server08 - 


# Run:
1) Modify the pom.xml to change the java.main.class
Client0X.java maps to Server0X.java

2) mvn compile exec:java
