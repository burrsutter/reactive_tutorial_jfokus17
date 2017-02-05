package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun8 {

    public static void main (String[] args) {
        Observable.just("Hallå","Hello","Hey Ya\'ll","Bonjour","Namaste")
                .map(ObservableFun8::transformIt)
                // .onErrorReturn(error -> "Aloha")

                .onErrorResumeNext(error -> {
                    System.out.println("Error: " + error);
                    return Observable.just("Aloha","Bonjour", "Namaste");
                })

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
            throw new RuntimeException("Ya\'ll Ain\'t a word");
        }
        return "X" + s;
    }

}
