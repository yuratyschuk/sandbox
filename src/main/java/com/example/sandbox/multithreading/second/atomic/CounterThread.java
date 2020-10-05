package com.example.sandbox.multithreading.second.atomic;

public class CounterThread extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(CounterAtomic.increment());
        }
    }
}
