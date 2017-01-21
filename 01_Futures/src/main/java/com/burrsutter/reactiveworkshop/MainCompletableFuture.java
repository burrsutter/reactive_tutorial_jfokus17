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
        return "SupplierStuff";
    }
} // MySupplier

class MyFunction1 implements Function<String, String> {
    @Override
    public String apply(String s) {
       System.out.println("MyFunction1");
       return s + " Item1";
    }
} // MyFunction1

class MyFunction2 implements Function<String, String> {
    @Override
    public String apply(String s) {
       System.out.println("MyFunction2");
       return s + " Item2";
    }
} // MyFunction2

class MyFunction3 implements Function<String, String> {
    @Override
    public String apply(String s) {
       System.out.println("MyFunction3");
       return s + " Item3";
    }
} // MyFunction2


public class MainCompletableFuture {
  public static void main (String args[]) throws Exception {
      ExecutorService exec = Executors.newSingleThreadExecutor();      
      CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(new MySupplier(), exec);
      
      CompletableFuture<String> cf2 = cf1.thenApply(new MyFunction1());
      
      CompletableFuture<String> cf3 = cf2.thenApply(new MyFunction2());

      CompletableFuture<String> cf4 = cf3.thenApply(new MyFunction3());

      System.out.println("cf4: " + cf4.get());
  }
}