package com.example.sandbox.multithreading.counter.reentrant;

import com.example.sandbox.multithreading.counter.synchronised.CounterSynchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainThread {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 100; i++) {
            service.submit(new CounterThreadLock());
        }


        service.shutdown();
        service.awaitTermination(2000, TimeUnit.MILLISECONDS);
        System.out.println(CounterSynchronized.getCount());
    }
}
