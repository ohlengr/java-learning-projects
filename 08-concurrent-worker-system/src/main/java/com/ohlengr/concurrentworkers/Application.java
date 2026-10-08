package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
        Runnable task1 = ()-> System.out.println("Task 1");
        Runnable task2 = ()-> System.out.println("Task 2");
        Runnable task3 = ()-> System.out.println("Task 3");

        Thread worker = new Thread(() -> {
            try {
                System.out.println("Worker waiting...");
                Runnable task = queue.take();
                System.out.println("Worker received task: " + task);
                task.run();
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        worker.start();

        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " is adding task...");
        queue.put(task1);

        worker.join();
    }
}
