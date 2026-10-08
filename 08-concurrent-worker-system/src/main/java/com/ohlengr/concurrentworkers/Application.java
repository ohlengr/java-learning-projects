package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
        Runnable task1 = () -> System.out.println("Task 1");
        Runnable task2 = () -> System.out.println("Task 2");
        Runnable task3 = () -> System.out.println("Task 3");

        try {
            queue.put(task1);
            queue.put(task2);
            queue.put(task3);

            System.out.println("Queue Size : " + queue.size());

            Thread worker = new Thread(()->{
                for (int i = 0; i < 3; i++) {
                    try {
                        Runnable task = queue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });

            worker.start(); //start worker

            worker.join(); //makes the main thread wait until this worker has finished all 3 tasks

            System.out.println("Queue Size : " + queue.size());

        }catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
