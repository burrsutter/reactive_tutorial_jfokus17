package com.burrsutter.reactiveworkshop;

import java.util.List;
import java.util.concurrent.ThreadFactory;

import rx.Single;
import rx.SingleSubscriber;
import java.util.stream.Collectors;

/**
 * Created by burr on 1/23/17.
 */
public class Server01Single {
    public static Single<String> getData(List<String> ids) {
        return Single.create(subscriber -> processRequest(subscriber,ids));
    }

    private static void processRequest(SingleSubscriber<? super String> subscriber, List<String> ids) {
        System.out.println("processing");
        // just a 2 second delay before the stream begins
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // This is a bad Single, it calls onSuccess more than once
        /*
        int count = 0;
        while(count++ < 3) {
            for (String id: ids) {
                subscriber.onSuccess("OK: " + id);
            }
        }
        */
        String stringOfIds = ids.stream().collect(Collectors.joining(", "));
        subscriber.onSuccess(stringOfIds);
    }
}
