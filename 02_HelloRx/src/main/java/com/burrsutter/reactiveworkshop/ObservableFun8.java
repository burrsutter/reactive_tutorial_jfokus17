package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun8 {

    public static void main (String[] args) {
        Observable.just("Hallå","Hello","Hey Ya\'ll","Bonjour")
                .map(ObservableFun8::transformIt)
                .onErrorReturn(error -> "Aloha")
                .subscribe(
                        new Subscriber<String>() {
                            @Override
                            public void onCompleted() {
                                System.out.println("Finished");
                            }

                            @Override
                            public void onError(Throwable e) {
                                System.out.println("Dang It: " +e);
                            }

                            @Override
                            public void onNext(String s) {
                                System.out.println(s);
                            }
                        }
                );
    }
    public static String transformIt(String s) {
        if (s.contains("Ya\'ll")) {
            throw new RuntimeException("No rednecks allowed ");
        }
        return "X" + s;
    }

}
