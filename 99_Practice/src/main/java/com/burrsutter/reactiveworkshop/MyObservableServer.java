package com.burrsutter.reactiveworkshop;

import java.util.Arrays;
import java.util.List;
import rx.Observable;

public class MyObservableServer {
    static List<String> ids = Arrays.asList("burrsutter", "yanaga", "realDonaldTrump");

    public static Observable<SocialData> getFeed() {
        return Observable.create(
        subscriber -> {
          subscriber.setProducer(request -> {
            int index = (int) request;
            String id = ids.get(index);
            // System.out.println(id);
            SocialData sd = SocialData.load(id);
            // System.out.println(sd);
            subscriber.onNext(sd);
          });
        });
    }
}