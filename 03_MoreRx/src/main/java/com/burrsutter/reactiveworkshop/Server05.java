package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Server05 {
    public static Observable<String> getFeed(List<String> ids) {
        return Observable.create(subscriber -> processRequest(subscriber,ids));
    }

    private static void processRequest(Subscriber<? super String> subscriber, List<String> ids) {
        System.out.println("processing");

        while(true) {
            for (String id: ids) {
                subscriber.onNext("OK: " + id);
            }
        }

    }  // processRequest
}
