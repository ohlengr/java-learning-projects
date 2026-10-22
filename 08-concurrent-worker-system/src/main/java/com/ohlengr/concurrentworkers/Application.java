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

        Runnable workerTask = () -> {
            try {
                while (true) {
                    WorkerTask task = queue.take();
                    if (task.taskId().equals("STOP")) {
                        System.out.println("Shutdown signal received. Worker stopping.");
                        break;
                    }
                    try {
                        task.action().run();
                        taskCounts.merge("completed", 1, Integer::sum);
                        taskResults.put(task.taskId(), new TaskResult(task.taskId(),"COMPLETED", null));
                    }catch (RuntimeException e) {
                        taskCounts.merge("failed", 1, Integer::sum);
                        taskResults.put(task.taskId(), new TaskResult(task.taskId(),"FAILED", e.getMessage()));
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

        worker1.join();
        worker2.join();
        worker3.join();

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
