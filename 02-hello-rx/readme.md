# Purpose:
Numerous examples of RxJava's Observable
* Hello01.java - Most simple Observable example
* Hello02.java - Single line, multiple items
* Hello03.java - but .just only supports 10 items, uncomment Halla to see error
* Hello04.java - uses .from to create an Observable from a List
* ObservableFun1.java - introduces .map function, series of transformations and string to integer
* ObservableFun2.java - more map function, doOnNext is like peek in Java 8 Streams API, it does not effect the stream.
* ObservableFun3.java - demonstrates a more complex .map function, including if-then-else statements
* ObservableFun4.java - demonstrates onNext, onCompleted
* ObservableFun5.java - .range and makes the point nothing happens until .subscribe
* ObservableFun6.java - uses a method reference with .map
* ObservableFun7.java - demonstrates how a RuntimeException might be thrown and handled in an Observable stream
* ObservableFun8.java - .onErrorResumeNext, provides an alternative stream should an error occur in the first one
* ObservableFun9.java - .skip and .take
* ObservableFun10.java - .flatMap, counts down 5 to 1
* ObservableReadFile.java - reads each row of a file
* Observable3Streams.java - .zip 3 streams into one

# Run:
1) Modify the pom.xml to change the java.main.class

2) mvn exec:java

