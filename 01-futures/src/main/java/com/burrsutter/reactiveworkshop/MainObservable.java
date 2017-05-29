package com.burrsutter.reactiveworkshop;

import rx.Observable;

public class MainObservable {

  public static Observable<String> getFeed() {
      return Observable.just("Item1", "Item2", "Item3");
  } // getFeed()

  public static void main(String args[]) {
      Observable<String> myObservable = getFeed();

      System.out.println("* Simply display each item");
      myObservable.subscribe(
          System.out::println
      );

      System.out.println("* Errors and completed/done");
      myObservable.subscribe(
        System.out::println, // data
        System.out::println, // errors
        () -> System.out.println("Done") // completed
      );

      
      System.out.println("* Filtered for only Item2 but appended X and Y");
      myObservable
       .map(s -> s + "X")
       .map(s -> s + "Y")
       .filter(s -> s.startsWith("Item2"))
       .subscribe(System.out::println);

  } // main
}