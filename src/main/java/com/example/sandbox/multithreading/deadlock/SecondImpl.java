package com.example.sandbox.multithreading.deadlock;

public class SecondImpl {

    public static void main(String[] args) {
        String first = "first";
        String second = "second";

        new Thread(() -> {
            try {
                deadlock(first, second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                deadlock(second, first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private static void deadlock(String a, String b) throws InterruptedException {
        synchronized (a) {
            Thread.sleep(1000);
            System.out.println("deadlock");
            synchronized (b) {
                System.out.println("not deadlock");
            }
        }
    }
}
