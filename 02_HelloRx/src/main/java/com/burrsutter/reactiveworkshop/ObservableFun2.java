package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun2 {
    public static void main (String[] args) {
        Observable.just("Hello, World ")
                .map(s -> s + " Burr")
                .map(s -> s.hashCode())
                .map(i -> Integer.toString(i))
                .subscribe(System.out::println);
    }
}
