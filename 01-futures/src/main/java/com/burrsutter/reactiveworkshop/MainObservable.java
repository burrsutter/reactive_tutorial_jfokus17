package com.burrsutter.reactiveworkshop;

import rx.Observable;

public class MainObservable {

  public static Observable<String> getFeed() {
      return Observable.just("Item1", "Item2", "Item3");
  } // getFeed()

  public static void main(String args[]) {
      Observable<String> myObservable = getFeed();
      myObservable.subscribe(
          System.out::println
      );

      /* 
      myObservable.subscribe(
        System.out::println, // data
        System.out::println, // errors
        () -> System.out.println("Done") // completed
      );
      */
      
      /* 
      myObservable
       .map(s -> s + " X")
       .map(s -> s + " Y")
       .filter(s -> s.startsWith("Item2"))
       .subscribe(System.out::println);
      */
  } // main
}