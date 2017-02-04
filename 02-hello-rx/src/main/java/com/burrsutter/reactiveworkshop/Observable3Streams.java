package com.burrsutter.reactiveworkshop;

import rx.Observable;
/**
 * Created by burr on 2/4/17.
 */
public class Observable3Streams {
    public static void main(String[] args) {
        Integer[] food =  { 10,  20,  30,  40};
        Integer[] hotel = {100, 100, 100, 110};
        Integer[] beer =  { 20,  30,  40,  50};

        Observable<Integer> foodStream = Observable.from(food);
        Observable<Integer> hotelStream = Observable.from(hotel);
        Observable<Integer> beerStream = Observable.from(beer);

        Observable<Integer> combinedStream = Observable.zip(
          foodStream, hotelStream, beerStream,
                (f, h, b) -> {
                    return f + h + (b * 2);
                }
        );

        combinedStream.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("COMPLETED")
        );
    }
}
