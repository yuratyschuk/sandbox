package com.example.sandbox.multithreading.third;

public class MainThread {


    public static void main(String[] args) {
        Recourse recourse1 = new Recourse("first");
        Recourse recourse2 = new Recourse("second");

        Thread thread1 = new Thread(() -> {
            synchronized (recourse1) {
                System.out.println("Thread1. Deadlock with recourse1: " + recourse1.getName());

                synchronized (recourse2) {
                    System.out.println("Thread2. Recourse2: " + recourse2.getName());
                }
            }
        });

       Thread thread2 = new Thread(() -> {
            synchronized (recourse2) {
                System.out.println("Thread2. Deadlock with recourse2: " + recourse2.getName());

                synchronized (recourse1) {
                    System.out.println("Thread2. Recourse1: " + recourse1.getName());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
