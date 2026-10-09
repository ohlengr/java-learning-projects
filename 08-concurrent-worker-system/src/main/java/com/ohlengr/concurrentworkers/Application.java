package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        Runnable task1 = ()-> System.out.println("Task 1");
        Runnable task2 = ()-> System.out.println("Task 2");
        Runnable task3 = ()-> System.out.println("Task 3");

        queue.put(task1);
        queue.put(task2);

        System.out.println("Main trying to add Task 3");

        Thread worker = new Thread(() -> {
            try {
                Thread.sleep(2000);
                Runnable task = queue.take();
                task.run();
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        worker.start();

        queue.put(task3);
        System.out.println("Task 3 added");

        worker.join();
    }
}
