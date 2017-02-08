package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client06 {
    public static void main(String[] args) throws InterruptedException {
        List<String> ids = Arrays.asList("burrsutter","yanaga", "Dolph_Lundgren");
        Observable<SocialData> feed = Server06.getFeed(ids);

        feed.subscribe(
             Client06::handleData,
             Client06::handleError
        );

    }
    public static void handleData(SocialData sd) {
        System.out.println(sd);
    }
    public static void handleError(Throwable t) {
        System.out.println("Danger Will Robinson " + t);
    }

}
