package com.example.sandbox.multithreading.thread.enter.word.with.daemon;

import lombok.SneakyThrows;

public class SecondThread extends Thread {

    private volatile int counter = 0;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            System.out.println("Application is working for " + ++counter);
            sleep(1000);
        }
    }

    public int getCounter() {
        return counter;
    }

}
