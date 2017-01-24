package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client05 {
    public static void main(String[] args) throws InterruptedException {
        List<String> ids = Arrays.asList("asdf","fdsa", "qwer");
        Observable<String> feed = Server05.getFeed(ids);

        feed.subscribeOn(Schedulers.io())
                .subscribe(Client05::doStuff);

        Thread.sleep(1000);
        System.out.println("Get as much done in 1 sec!");


    }
    public static void doStuff(String s) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(s);

    }

}
