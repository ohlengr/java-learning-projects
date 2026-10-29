package com.ohlengr.concurrentworkers;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Worker implements Runnable {
    private final BlockingQueue<QueueMessage> queue;
    private final ConcurrentHashMap<String, Integer> taskCounts;
    private final ConcurrentHashMap<String, TaskResult> taskResults;

    public  Worker(BlockingQueue<QueueMessage> queue,  ConcurrentHashMap<String, Integer> taskCounts, ConcurrentHashMap<String, TaskResult> taskResults) {
        this.queue = queue;
        this.taskCounts = taskCounts;
        this.taskResults = taskResults;
    }

    @Override
    public void run() {
        try {
            while (true) {
                QueueMessage message = queue.take();
                if (message == ShutdownSignal.INSTANCE) {
                    System.out.println("Shutdown signal received. Worker stopping.");
                    break;
                }

                if (!(message instanceof WorkerTask task)) {
                    throw new IllegalStateException("Unknown queue message type");
                }

                try {
                    task.action().run();
                    taskCounts.merge("completed", 1, Integer::sum);
                    taskResults.put(task.taskId(), new TaskResult(task.taskId(), "COMPLETED", null));
                }catch (RuntimeException e) {
                    taskCounts.merge("failed", 1, Integer::sum);
                    taskResults.put(task.taskId(), new TaskResult(task.taskId(), "FAILED", e.getMessage()));
                }
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Worker interrupted. Shutting down...");
        }
    }
}
