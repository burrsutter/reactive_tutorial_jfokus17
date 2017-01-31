package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by burr on 1/31/17.
 */
public class ObservableFun10 {
    public static void main(String[] args) {
        Observable.range(1, 5)
                .flatMap(v -> Observable.just(v).delay(6 - v, TimeUnit.SECONDS))
                .toBlocking()
                .subscribe(System.out::println);

    }
}
