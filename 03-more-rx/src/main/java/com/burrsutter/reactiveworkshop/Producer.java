package com.burrsutter.reactiveworkshop;

import rx.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by burr on 2/5/17.
 */
public class Producer {
    private final Random random = new Random();
    private AtomicInteger counter = new AtomicInteger();
    private int last = 20;

    public Observable<Integer[]> asObservable() {
        return Observable
                .interval(100, TimeUnit.MILLISECONDS)
                .map(i -> {
                    int y = genNewValue();
                    Integer[] x = new Integer[] {counter.incrementAndGet(),y};
                    System.out.println("Producing: " + x[0] + " " + x[1]);
                    return x;
                });
    }

    private int genNewValue() {
        boolean neg = random.nextBoolean();
        int diff = random.nextInt(3);
        last += (neg ? -diff : diff);
        return last;
    }
}
