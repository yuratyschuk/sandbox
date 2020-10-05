package com.example.sandbox.multithreading.second.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1000);

        for(int i = 0; i < 1000; i++) {
            service.execute(new CounterThread());
        }

        System.out.println(CounterAtomic.getCount());

        service.shutdown();
    }
}
