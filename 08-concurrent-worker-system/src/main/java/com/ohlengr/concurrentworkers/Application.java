package com.ohlengr.concurrentworkers;

import java.util.concurrent.*;
import java.util.concurrent.ConcurrentHashMap;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> taskCounts = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, TaskResult> taskResults = new ConcurrentHashMap<>();
        taskCounts.put("completed", 0);
        taskCounts.put("failed", 0);
        int numberOfTasks = 100_000;
        BlockingQueue<WorkerTask> queue = new ArrayBlockingQueue<>(3);
        WorkerTask stop = new WorkerTask("STOP", () -> {});

        Worker worker1 = new Worker(queue,taskCounts,taskResults);
        Worker worker2 = new Worker(queue,taskCounts,taskResults);
        Worker worker3 = new Worker(queue,taskCounts,taskResults);

        Thread workerThread1 = new Thread(worker1);

        Thread workerThread2 = new Thread(worker2);

        Thread workerThread3 = new Thread(worker3);

        workerThread1.start();
        workerThread2.start();
        workerThread3.start();

        for (int i = 0; i < numberOfTasks; i++) {
            String taskId = "task-" + (i + 1);
            queue.put(new  WorkerTask(taskId, () -> {
                for (int j = 0; j < 100; j++) {
                    Math.sqrt(j);
                }
            }));
        }

        String failureTaskId = "task-failure-1";
        queue.put(new WorkerTask(failureTaskId, () -> {
            throw new RuntimeException("Test task failure");
        }));

        queue.put(stop);
        queue.put(stop);
        queue.put(stop);

        workerThread1.join();
        workerThread2.join();
        workerThread3.join();

        System.out.println("Expected: " + numberOfTasks);
        System.out.println("Completed Tasks: " + taskCounts.get("completed"));
        System.out.println("Failed Tasks: " + taskCounts.get("failed"));

        System.out.println("Stored results: " + taskResults.size());
        System.out.println("Task 1 result: " + taskResults.get("task-1"));
        System.out.println("Failure Task 1 result: " + taskResults.get(failureTaskId));

        System.out.println(
                "Counts match: " +
                        (taskResults.size() == taskCounts.get("completed") + taskCounts.get("failed"))
        );

        System.out.println("Main thread finished.");
    }
}
