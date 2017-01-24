package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client03 {
    public static void main(String[] args) {
        List<String> ids = Arrays.asList("asdf","fdsa", "qwer");
        Observable<String> feed = Server03.getFeed(ids);
        feed.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error: " + e);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
                if (s.equals("OK: qwer")) {
                    System.out.println("Time to wrap up");
                    unsubscribe();  // this does not work, see 04
                }
            }
        });
    }
}
