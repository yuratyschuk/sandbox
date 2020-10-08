package com.example.sandbox.multithreading.counter.synchronised;

public class CounterSynchronized {

    private static int count = 0;

    public static synchronized int increment() {
        return ++count;
    }

    public static int getCount() {
        return count;
    }
}
