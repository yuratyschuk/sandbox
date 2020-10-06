package com.example.sandbox.multithreading.first.without.daemon;

import lombok.SneakyThrows;

public class SecondThread extends Thread {

    private volatile int counter = 0;

    private volatile boolean isThreadActive = true;

    @SneakyThrows
    @Override
    public void run() {
        while (isThreadActive) {
            System.out.println("Application is working for " + ++counter);

            sleep(1000);
        }
    }

    public int getCounter() {
        return counter;
    }


    public void setThreadActive(boolean isThreadActive) {
        this.isThreadActive = isThreadActive;
    }

}