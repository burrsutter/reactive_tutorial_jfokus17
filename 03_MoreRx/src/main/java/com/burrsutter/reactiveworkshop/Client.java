package com.burrsutter.reactiveworkshop;

import rx.Observable;
import java.util.List;
import java.util.Arrays;

public class Client {

    public static void main(String args[]) {

        Observable<String> hello = Observable.just("Hallå");

        System.out.println("* Hello Observable *");

        hello.subscribe(System.out::println);

    }
}