package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client08 {
    public static void main(String[] args)  {
        List<String> ids = Arrays.asList("asdf","fdsa", "qwer");
        Observable<SocialData> feed = Server08.getFeed(ids);

        feed.subscribe(
                Client08::handleData,
                Client08::handleError, Client08::handleCompleted
        );

    }
    public static void handleData(SocialData sd) {
        System.out.println("received " + sd);
    }
    public static void handleError(Throwable t) {
        System.out.println("Danger Will Robinson " + t);
    }
    public static void handleCompleted() {
        System.out.println("Completed");
    }
}
