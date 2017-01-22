package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun3 {
    public static void main (String[] args) {
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
