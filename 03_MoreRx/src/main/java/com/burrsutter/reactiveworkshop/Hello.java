package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by burr on 1/23/17.
 */
public class Hello {
        public static void main(String args[]) throws InterruptedException {


                Observable<String> hello = Observable.just("Hallå","Hello","Bonjour");

                System.out.println("* Hello Observable *");

                hello
                        .subscribeOn(Schedulers.io())
                        .map(String::length)
                        .subscribe( l -> {
                    System.out.println("received: " + l);
                    System.out.println("on thread " + Thread.currentThread().getName());

                });

            Thread.sleep(1000);

        }
}