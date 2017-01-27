package com.burrsutter.reactiveworkshop;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.math.BigDecimal;

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {        
        //Thread.sleep(10);
        return "Item";
    }

}

public class MainFuture {

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<String> f1 = exec.submit(new MyCallable());

        System.out.println("Done1:" + f1.isDone());  //False
        String f1result = f1.get(); // blocks!

        Future<String> f2 = exec.submit(new MyCallable());
        String f2result = f1result + " " + f2.get(); // blocks!
        
        Future<String> f3 = exec.submit(new MyCallable());
        while (!f3.isDone() && !f3.isCancelled()) { // loop util done
            System.out.println("yielding");
            Thread.yield();
        }
        String f3result = f2result + " " + f3.get();
        System.out.println("Result3: " + f3result); 

        
    }
}