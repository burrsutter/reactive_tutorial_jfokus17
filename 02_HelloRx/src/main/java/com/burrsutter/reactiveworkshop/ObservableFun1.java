package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun1 {
    public static void main (String[] args) {
        Observable.just("1234567890")
                .map(s -> s.substring(5))
                .map(i -> new Integer(i))
                .subscribe(i -> System.out.println(i));
    }
}
