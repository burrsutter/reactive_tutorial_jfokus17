package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun5 {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> source = Observable.range(1, 5);

        Subscriber<Integer> consumer = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Done");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Error: " + e);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("I see: " + integer);
            }
        };

        System.out.println("Nothing happens until subscribe is called");
        Thread.sleep(1000);
        source.subscribe(consumer);


    }
}
