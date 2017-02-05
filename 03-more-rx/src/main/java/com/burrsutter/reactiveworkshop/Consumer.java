package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * Created by burr on 2/5/17.
 */
public class Consumer {
    private static final Producer producer = new Producer();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        Observable x = producer.asObservable();
        producer.asObservable()
                .onBackpressureDrop(item -> System.out.println("DROPPED: " + item[0] + " " + item[1]))
                .observeOn(Schedulers.computation())
                .concatMap(Consumer::convertToString)
                .subscribe(result -> System.out.println("Consuming: " + result), System.err::println);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Observable<String> convertToString(Integer[] item) {
        Future<String> future = executor.submit(() -> {
            nap();
            String x = item[0] + " " + item[1];

            return x;
        });

        return Observable.from(future);
    }

    private static void nap() {
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
