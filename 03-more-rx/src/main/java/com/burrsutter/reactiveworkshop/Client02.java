package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client02 {
    public static void main(String[] args) {
        List<String> ids = Arrays.asList("asdf","fdsa", "qwer");
        Observable<String> feed = Server02.getFeed(ids);
        feed.subscribe(
                System.out::println, // data
                System.out::println, // errors
                () -> System.out.println("Completed")
        );
    }
}
