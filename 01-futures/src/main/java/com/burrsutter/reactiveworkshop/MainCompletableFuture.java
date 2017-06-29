package com.burrsutter.reactiveworkshop;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MySupplier implements Supplier<String> {
    @Override
    public String get() {
        System.out.println("MySupplier");
        return "Item1";
    }
} // MySupplier

class MyFunction1 implements Function<String, String> {
    @Override
    public String apply(String s) {
//       System.out.println("MyFunction1");
       return s + " Item2";
    }
} // MyFunction1

class MyFunction2 implements Function<String, String> {
    @Override
    public String apply(String s) {
//       System.out.println("MyFunction2");
       return s + " Item3";
    }
} // MyFunction2



public class MainCompletableFuture {
  public static void main (String args[]) throws Exception {
      ExecutorService exec = Executors.newSingleThreadExecutor();      
      CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(new MySupplier(), exec);
      System.out.println("cf1: " + cf1.get());

      CompletableFuture<String> cf2 = cf1.thenApply(new MyFunction1());
      System.out.println("cf2: " + cf2.get());
      
      CompletableFuture<String> cf3 = cf2.thenApply(new MyFunction2());
      System.out.println("cf3: " + cf3.get());


  }
}