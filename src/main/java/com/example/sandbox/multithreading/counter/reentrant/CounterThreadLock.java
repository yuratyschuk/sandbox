package com.example.sandbox.multithreading.counter.reentrant;

public class CounterThreadLock extends Thread {


    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(CounterLock.increment());
        }
    }
}
