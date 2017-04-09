# Purpose:
The most common question for a new learner of RxJava, why not
Java's built-in Future?

A side-by-side comparision of 
  * Future - it blocks
  Note: Vert.x offers io.vertx.core.Future which is async, non-blocking
  * CompletableFuture - assumes single return values
  * Observable -  allows for multiple return values

# Run:
1) Modify the pom.xml to change the java.main.class

2) mvn exec:java

