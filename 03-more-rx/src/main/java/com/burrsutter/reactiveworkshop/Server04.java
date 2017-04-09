package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Server04 {
    public static Observable<String> getFeed(List<String> ids) {
        return Observable.create(subscriber -> processRequest(subscriber,ids));
    }

    private static void processRequest(Subscriber<? super String> subscriber, List<String> ids) {
        System.out.println("processing");

        int count = 0;
        
        // if nobody is subscribing, stop broadcasting
        while(!subscriber.isUnsubscribed()) {
            count++;
            for (String id: ids) {
                subscriber.onNext(count + "-OK: " + id);
            }
        }
    }  // processRequest
}
