package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun1 {
    public static void main (String[] args) {
        // remember, nothing happens until subscribe
        // each .map applies a function
        Observable.just("12345")
                .map(s -> s.substring(3)) // "45"
                .map(i -> new Integer(i)) // 45
                .map(a -> a * 2) // 90
                .subscribe(x -> {
                    System.out.println(x);
                    System.out.println(x.getClass().getName());
                }); // 90
    }
}
