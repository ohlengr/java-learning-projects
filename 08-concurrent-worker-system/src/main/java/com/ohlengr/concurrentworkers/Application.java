package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> taskCounts = new ConcurrentHashMap<>();
        taskCounts.put("completed", 0);
        taskCounts.put("failed", 0);
        int numberOfTasks = 100_000;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
        Runnable stop = () -> {};

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
                        taskCounts.merge("completed", 1, Integer::sum);
                    }catch (RuntimeException e) {
                        taskCounts.merge("failed", 1, Integer::sum);
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

        for (int i = 0; i < numberOfTasks; i++) {
            queue.put(() -> {
                for (int j = 0; j < 100; j++) {
                    Math.sqrt(j);
                }
            });
        }

        queue.put(() -> {
            throw new RuntimeException("Test task failure");
        });

        queue.put(stop);
        queue.put(stop);
        queue.put(stop);

        worker1.join();
        worker2.join();
        worker3.join();

        System.out.println("Expected: " + numberOfTasks);
        System.out.println("Completed Tasks: " + taskCounts.get("completed"));
        System.out.println("Failed Tasks: " + taskCounts.get("failed"));

        System.out.println("Main thread finished.");
    }
}
