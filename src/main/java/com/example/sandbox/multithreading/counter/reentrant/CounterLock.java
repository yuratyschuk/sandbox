package com.example.sandbox.multithreading.counter.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {

    private static int count = 0;

    private static final ReentrantLock lock = new ReentrantLock();

    public static int increment() {
        lock.lock();
        ++count;
        lock.unlock();
        return count;
    }

    public static int getCount() {
        return count;
    }
}
