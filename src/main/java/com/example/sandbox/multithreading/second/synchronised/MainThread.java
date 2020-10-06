package com.example.sandbox.multithreading.second.synchronised;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for(int i = 0; i < 1000; i++) {
            executorService.submit(new CounterThread());
        }

        System.out.println(CounterSynchronized.getCount());

        executorService.shutdown();
    }
}
