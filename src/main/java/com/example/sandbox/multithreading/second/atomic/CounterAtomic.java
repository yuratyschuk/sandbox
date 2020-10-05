package com.example.sandbox.multithreading.second.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic {

    private static final AtomicInteger count = new AtomicInteger(0);

    public static int increment() {
        return count.incrementAndGet();
    }

    public static int getCount() {
        return count.get();
    }
}
