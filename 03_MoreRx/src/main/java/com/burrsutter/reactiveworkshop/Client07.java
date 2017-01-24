package com.burrsutter.reactiveworkshop;

import com.sun.corba.se.spi.activation.Server;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client07 {
    public static void main(String[] args) throws InterruptedException {
        List<String> ids = Arrays.asList("asdf","fdsa", "qwer");
        Observable<SocialData> feed = Server07.getFeed(ids);

        feed.onErrorResumeNext(throwable -> handleFallback(throwable, ids))
                .subscribe(
                        Client07::handleData,
                        Client07::handleError
                );
    }
    public static void handleData(SocialData sd) {
        System.out.println(sd);
    }
    public static void handleError(Throwable t) {
        System.out.println("Danger Will Robinson " + t);
    }
    public static Observable<SocialData> handleFallback(Throwable t, List<String> ids) {
        System.out.println("Danger be Damned! Go back for more");
        return Server07.getFeed(ids);
    }
}
