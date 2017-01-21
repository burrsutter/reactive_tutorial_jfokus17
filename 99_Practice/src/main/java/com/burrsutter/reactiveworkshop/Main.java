package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

public class Main {
    public static void main(String[] args) {
        Observable<SocialData> feed = MyObservableServer.getFeed();
        feed.subscribe(System.out::println);

/*
        feed.subscribe(new Subscriber<SocialData>() {
        @Override
        public void onStart() {
          request(0);
        }

        @Override
        public void onCompleted() {
          System.out.println("completed");
        }

        @Override 
        public void onError(Throwable throwable) {
          System.out.println("Crap! " + throwable);
          throwable.printStackTrace();
        }

        @Override
        public void onNext(SocialData socialData) {
          System.out.println(socialData);
        }
    });
*/
    }  // main
} // Main