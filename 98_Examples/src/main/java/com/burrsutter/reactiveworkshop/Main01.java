package com.burrsutter.reactiveworkshop;

import rx.Observable;

public class Main01 {
    public static void main (String args[]) {
        Observable.just("Hello, World ")
            .map(s -> s + " Burr")
            .map(s -> s.hashCode())
            .map(i -> Integer.toString(i))
            .subscribe(System.out::println);

        Observable.just("1234567890")
            .map(s -> s.substring(5))
            .map(i -> new Integer(i))
            .subscribe(i -> System.out.println(i));

        Observable.just("trim off 1st word")
          .map(s -> {
            if (s.contains(" ")) {
                return s.substring(s.indexOf(" ")).trim();
            } else
                return "";
          }) 
          .subscribe(System.out::println);

        Observable.just("trim off 1st word")
          .map(s -> s.contains(" ") ? s.substring(s.indexOf(" ")).trim() : "")
          .subscribe(System.out::println);
          
    }
}