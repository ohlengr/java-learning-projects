package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
        Runnable task1 = ()-> System.out.println("Task 1");
        Runnable task2 = ()-> System.out.println("Task 2");
        Runnable task3 = ()-> System.out.println("Task 3");
        Runnable stop = () -> {};

        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    Runnable task = queue.take();
                    if (task == stop) {
                        System.out.println("Shutdown signal received. Worker stopping.");
                        break;
                    }
                    task.run();
                    System.out.println("Queue size after processing task: " + queue.size());
                }
            }catch (InterruptedException e) {
                System.out.println("Worker interrupted. Shutting down...");
            }
        });
        worker.start();

        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        queue.put(stop);

        worker.join();

        System.out.println("Main thread finished.");
    }
}
