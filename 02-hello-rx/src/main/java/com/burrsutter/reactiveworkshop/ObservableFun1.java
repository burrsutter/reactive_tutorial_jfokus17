package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun1 {
    public static void main (String[] args) {
        Observable.just("12345")
                .map(s -> s.substring(3))
                .map(i -> new Integer(i))
                .map(a -> a * 2)
                .subscribe(x -> System.out.println(x));
    }
}
