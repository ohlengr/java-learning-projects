package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
        Runnable task1 = ()-> System.out.println("Task 1");
        Runnable task2 = ()-> System.out.println("Task 2");
        Runnable task3 = ()-> System.out.println("Task 3");
        Runnable stop = () -> {};
        Runnable task4 = () -> {
            throw new RuntimeException("Task 4 failed!");
        };

        Runnable workerTask = () -> {
            try {
                while (true) {
                    Runnable task = queue.take();
                    if (task == stop) {
                        System.out.println("Shutdown signal received. Worker stopping.");
                        break;
                    }
                    try {
                        task.run();
                        System.out.println("Queue size after processing task: " + queue.size());
                    }catch (RuntimeException e) {
                        System.out.println("Queue task failed with error: " + e.getMessage());
                    }
                }
            }catch (InterruptedException e) {
                System.out.println("Worker interrupted. Shutting down...");
            }
        };

        Thread worker1 = new Thread(workerTask);

        Thread worker2 = new Thread(workerTask);

        Thread worker3 = new Thread(workerTask);

        worker1.start();
        worker2.start();
        worker3.start();

        queue.put(task1);
        queue.put(task2);
        queue.put(task3);
        queue.put(task4);

        queue.put(stop);
        queue.put(stop);
        queue.put(stop);

        worker1.join();
        worker2.join();
        worker3.join();

        System.out.println("Main thread finished.");
    }
}
