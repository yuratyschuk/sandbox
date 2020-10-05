package com.example.sandbox.multithreading.first.without.daemon;

import java.util.Scanner;

public class MainThread {

    public static void main(String[] args) {
        SecondThread secondThread = new SecondThread();
        secondThread.start();

        System.out.println("Enter something: ");

        Scanner scanner = new Scanner(System.in);
        String something = scanner.nextLine();

        System.out.println("You enter: " + something);

        secondThread.setThreadActive(false);
        System.out.println("Application was working for: " + secondThread.getCounter());
    }
}