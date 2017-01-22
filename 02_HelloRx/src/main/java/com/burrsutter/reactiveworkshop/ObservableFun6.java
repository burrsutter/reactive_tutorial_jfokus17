package com.burrsutter.reactiveworkshop;

import rx.Observable;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun6 {

    public static void main (String[] args) {

        Observable.just("Hallå","Hello","Bonjour","Hey Ya\'ll")
                .map(ObservableFun6::transformIt)
                .subscribe(System.out::println);

    }
    public static String transformIt(String s) {
            return "X" + s;
    }

}
