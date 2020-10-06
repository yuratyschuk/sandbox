package com.example.sandbox.multithreading.third;

import java.util.concurrent.CountDownLatch;

public class MainThread {

//    private static final CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args)  {
        String recourse1 = "first";

        String recourse2 = "second";


        for(int i = 0; i < 100; i++) {
            Thread thread1 = new Thread(() -> {
//                countDownLatch.countDown();
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                synchronized (recourse1) {
                    System.out.println("Thread1. Deadlock with recourse1: " + recourse1);

                    synchronized (recourse2) {
                        System.out.println("Thread1. Recourse2: " + recourse2);
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                synchronized (recourse2) {
                    System.out.println("Thread2. Deadlock with recourse2: " + recourse2);

                    synchronized (recourse1) {
                        System.out.println("Thread2. Recourse1: " + recourse1);
                    }
                }
            });

            thread1.start();
            thread2.start();
        }
    }
}
