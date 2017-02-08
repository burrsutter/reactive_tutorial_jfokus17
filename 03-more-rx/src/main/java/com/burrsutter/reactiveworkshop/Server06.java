package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Server06 {
    public static Observable<SocialData> getFeed(List<String> ids) {
        return Observable.create(subscriber -> processRequest(subscriber,ids));
    }

    private static void processRequest(Subscriber<? super SocialData> subscriber, List<String> ids) {
        System.out.println("processing");

        while(true) {
            for (String id: ids) {
                subscriber.onNext(SocialData.load(id));
            }
        }
    }  // processRequest
}
