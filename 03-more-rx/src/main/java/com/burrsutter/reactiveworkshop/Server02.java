package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Server02 {
    public static Observable<String> getFeed(List<String> ids) {
        return Observable.create(subscriber -> processRequest(subscriber,ids));
    }

    private static void processRequest(Subscriber<? super String> subscriber, List<String> ids) {
        System.out.println("processing");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = 0;
        while(count++ < 3) {
            for (String id: ids) {
                subscriber.onNext("OK: " + id);
            }
        }
        subscriber.onNext("More Stuff");
        subscriber.onCompleted();
    }  // processRequest
}
