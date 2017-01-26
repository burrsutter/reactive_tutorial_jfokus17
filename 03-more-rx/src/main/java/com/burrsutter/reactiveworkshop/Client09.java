package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client09 {
    public static void main(String[] args)  {
        List<String> ids = Arrays.asList(
                "burrsutter",
                "yanaga",
                "rafabene",
                "Dolph_Lundgren",
                "MickeNyqvist",
                "realDonaldTrump"
        );
        Observable<SocialData> feed = Server09.getFeed(ids);

        feed.subscribe(
                Client09::handleData,
                Client09::handleError, Client09::handleCompleted
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
