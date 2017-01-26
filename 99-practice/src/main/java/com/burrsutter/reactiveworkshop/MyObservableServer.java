package com.burrsutter.reactiveworkshop;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class MyObservableServer {
    static List<String> ids = Arrays.asList("burrsutter", "yanaga", "realDonaldTrump", "venkat_s");

    public static Observable<SocialData> getFeed() {
        return Observable.create(subscriber -> processIt(subscriber, ids));
    }  // getFeed

    public static void processIt(Subscriber<? super SocialData> subscriber, List<String> ids) {
        ids.stream()
                .map(SocialData::load)
                .forEach(subscriber::onNext);
        subscriber.onCompleted();
    } // processIt
}