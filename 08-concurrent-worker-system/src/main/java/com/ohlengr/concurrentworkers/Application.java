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
                for (int i = 0; i < 3; i++) {
                    Runnable task = queue.take();
                    task.run();
                }
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        worker.start();

        queue.put(task1);
        queue.put(task2);
        queue.put(task3);

        worker.join();
    }
}
