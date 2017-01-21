package com.burrsutter.reactiveworkshop;

import rx.Observable;
import java.util.List;
import java.util.Arrays;

public class Hello02 {

    public static void main(String args[]) {
        System.out.println(Hello02.class.getSimpleName());

        System.out.println("* Hello Observable - 4 items *");
        Observable.just("Hallå","Hello","Bonjour","Hey Ya\'ll").subscribe(System.out::println);            
    }
}