package com.burrsutter.reactiveworkshop;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by burr on 1/22/17.
 */
public class ObservableFun7 {

    public static void main (String[] args) {
        System.out.println("Simple Error Example");
        Observable.just("Hello!")
                .map(input -> { throw new RuntimeException("D'oh"); })
                .subscribe(
                        System.out::println,
                        error -> System.out.println("Error! " + error)
                );

        System.out.println("Better Error Example");
        Observable.just("Hallå","Hello","Hey Ya\'ll","Bonjour")
                .map(ObservableFun7::transformIt)
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
        if (s.contains("\'")) {
            throw new RuntimeException("No rednecks allowed ");
        }
        return "X" + s;
    }

}
