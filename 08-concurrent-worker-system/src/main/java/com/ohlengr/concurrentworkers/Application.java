package com.ohlengr.concurrentworkers;

public class Application {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " thread started");
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " worker thread started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " worker thread end");
        });
        thread.start(); // start the thread
        // thread.run(); // call the thread
        System.out.println(Thread.currentThread().getName() + " thread end");
    }
}
