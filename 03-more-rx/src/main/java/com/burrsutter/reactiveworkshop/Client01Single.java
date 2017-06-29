package com.burrsutter.reactiveworkshop;


// import rx.Observable;
import rx.Single;

import java.util.Arrays;
import java.util.List;

/**
 * Created by burr on 1/23/17.
 */
public class Client01Single {
    public static void main(String[] args) {
        List<String> ids = Arrays.asList("asdf","fdsa", "qwer");
        Single<String> result = Server01Single.getData(ids);

        result.subscribe(System.out::println);
    }

}
