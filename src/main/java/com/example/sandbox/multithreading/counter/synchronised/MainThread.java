package com.example.sandbox.multithreading.counter.synchronised;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainThread {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for(int i = 0; i < 100; i++) {
            executorService.submit(new CounterThread());
        }


        executorService.shutdown();
        executorService.awaitTermination(2000, TimeUnit.MILLISECONDS);
        System.out.println(CounterSynchronized.getCount());
    }
}
