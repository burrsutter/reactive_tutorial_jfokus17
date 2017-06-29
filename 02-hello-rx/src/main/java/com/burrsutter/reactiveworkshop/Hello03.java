package com.burrsutter.reactiveworkshop;

import rx.Observable;
import java.util.List;
import java.util.Arrays;

public class Hello03 {

    public static void main(String args[]) {
        System.out.println(Hello03.class.getSimpleName());


        System.out.println("* just() is limited to 10 items *");
        Observable.just(
            "Hallå",
            "Hello",
            "Bonjour",
            "Hey Ya\'ll",
            "Hola",
            "Ola",
            "Aloha",
            "Howdy",
            "Wassup",
            //"Hallo",  // just only supports up to 10 parameters
            "Ciao").subscribe(System.out::println);

            
    }
}