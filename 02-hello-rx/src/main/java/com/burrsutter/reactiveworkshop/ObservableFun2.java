package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun2 {
    public static void main (String[] args) {
        /* The output 
            Hello, World 
            Hello, World  Burr
            Integer
            1400957567
            String
            1400957567
        */
        Observable.just("Hello, World ")
                .doOnNext(System.out::println)
                .map(s -> s + " Burr") 
                .doOnNext(System.out::println)
                .map(s -> s.hashCode())
                .doOnNext(x -> System.out.println(x.getClass().getSimpleName()))
                .doOnNext(System.out::println)                
                .map(i -> Integer.toString(i))
                .doOnNext(x -> System.out.println(x.getClass().getSimpleName()))
                .subscribe(System.out::println);
    }
}
