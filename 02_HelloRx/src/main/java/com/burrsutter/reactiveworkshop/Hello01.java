package com.burrsutter.reactiveworkshop;

import rx.Observable;
import java.util.List;
import java.util.Arrays;

public class Hello01 {

    public static void main(String args[]) {
        System.out.println(Hello01.class.getSimpleName());

        Observable<String> hello = Observable.just("Hallå");

        System.out.println("* Hello Observable *");

        hello.subscribe(System.out::println);

    }
}