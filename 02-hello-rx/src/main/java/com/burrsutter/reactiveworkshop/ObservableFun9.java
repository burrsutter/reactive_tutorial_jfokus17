package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun9 {

    public static void main (String[] args) {
        List<String> greetings = Arrays.asList(
                "Hallå",
                "Hello",
                "Bonjour",
                "Hey Ya\'ll",
                "Hola",
                "Ola",
                "Aloha",
                "Howdy",
                "Wassup",
                "Hallo",
                "Ciao",
                "Namaste"
        );
        Observable.from(greetings)
                .skip(2)
                .take(3)
                .subscribe(System.out::println);
    }

}
