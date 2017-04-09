package com.burrsutter.reactiveworkshop;

import java.util.List;
import java.util.concurrent.ThreadFactory;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by burr on 1/23/17.
 */
public class Server01 {
    public static Observable<String> getFeed(List<String> ids) {
        return Observable.create(subscriber -> processRequest(subscriber,ids));
    }

    private static void processRequest(Subscriber<? super String> subscriber, List<String> ids) {
        System.out.println("processing");
        // just a 2 second delay before the stream begins
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // infinite loop on purpose
        while(true) {
            for (String id: ids) {
                subscriber.onNext("OK: " + id);
            }
        }

    }
}
