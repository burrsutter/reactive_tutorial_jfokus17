package com.burrsutter.reactiveworkshop;

import rx.Observable;
import java.util.List;
import java.util.Arrays;

public class Hello01 {

    public static void main(String args[]) {
        System.out.println(Hello01.class.getSimpleName());

        Observable<String> hello = Observable.just("Item1","Item2", "Item3");
        // nothing happens until subscribe is called
        System.out.println("* Hello Observable *");
        
        // :: Java 8 method reference
        hello.subscribe(System.out::println);

    }
}