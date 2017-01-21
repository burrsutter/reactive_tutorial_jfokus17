package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

public class Main {
    public static void main(String[] args) {
        Observable<SocialData> feed = MyObservableServer.getFeed();
        feed.subscribe(System.out::println);
    }  // main
} // Main