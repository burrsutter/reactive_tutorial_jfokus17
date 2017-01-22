package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun4 {
    public static void main (String[] args) {
        Observable.just("Hallå","Hello","Bonjour","Hey Ya\'ll")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("All Done");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("D'oh " + e);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });
    }
}
