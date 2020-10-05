package com.example.sandbox.multithreading.first.with.daemon;

import java.util.Scanner;

public class MainThread {

    public static void main(String[] args) {
        SecondThread secondThread = new SecondThread();
        secondThread.setDaemon(true);
        secondThread.start();

        System.out.println("Enter something: ");

        Scanner scanner = new Scanner(System.in);
        String something = scanner.nextLine();

        System.out.println("You enter: " + something);
        System.out.println("Application was working for: " + secondThread.getCounter());
    }
}
