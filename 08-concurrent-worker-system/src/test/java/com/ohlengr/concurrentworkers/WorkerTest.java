package com.ohlengr.concurrentworkers;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

    @Test
    public void testWorkerProcessesSuccessfulTask() {
        BlockingQueue<QueueMessage> queue = new ArrayBlockingQueue<>(2);
        ConcurrentHashMap<String, Integer> taskCounts = new ConcurrentHashMap<>();
        taskCounts.put("completed", 0);
        taskCounts.put("failed", 0);
        ConcurrentHashMap<String, TaskResult> taskResults = new ConcurrentHashMap<>();
        Worker worker = new Worker(queue, taskCounts, taskResults);
        WorkerTask workerTask = new WorkerTask("task-1", () -> {});
        ShutdownSignal stopTask = ShutdownSignal.INSTANCE;

        queue.add(workerTask);
        queue.add(stopTask);

        worker.run();

        assertEquals(1, taskCounts.get("completed"));
        assertEquals(0, taskCounts.get("failed"));
        assertEquals("COMPLETED", taskResults.get("task-1").status());
    }

    @Test
    public void testWorkerContinuesAfterTaskFailure() {
        BlockingQueue<QueueMessage> queue = new ArrayBlockingQueue<>(3);
        ConcurrentHashMap<String, Integer> taskCounts = new ConcurrentHashMap<>();
        taskCounts.put("completed", 0);
        taskCounts.put("failed", 0);
        ConcurrentHashMap<String, TaskResult> taskResults = new ConcurrentHashMap<>();
        Worker worker = new Worker(queue, taskCounts, taskResults);
        WorkerTask failedTask = new WorkerTask("task-failure-1", () -> {
            throw new RuntimeException("Test task failure");
        });
        WorkerTask completedTask = new WorkerTask("task-1", () -> {});
        ShutdownSignal stopTask = ShutdownSignal.INSTANCE;

        queue.add(failedTask);
        queue.add(completedTask);
        queue.add(stopTask);

        worker.run();

        assertEquals(1, taskCounts.get("completed"));
        assertEquals(1, taskCounts.get("failed"));
        assertEquals("COMPLETED", taskResults.get("task-1").status());
        assertEquals("FAILED", taskResults.get("task-failure-1").status());
        assertEquals("Test task failure", taskResults.get("task-failure-1").errorMessage());
    }

    @Test
    public void testWorkerStopsWhenInterrupted() throws InterruptedException {
        BlockingQueue<QueueMessage> queue = new ArrayBlockingQueue<>(3);
        ConcurrentHashMap<String, Integer> taskCounts = new ConcurrentHashMap<>();
        taskCounts.put("completed", 0);
        taskCounts.put("failed", 0);
        ConcurrentHashMap<String, TaskResult> taskResults = new ConcurrentHashMap<>();

        Worker worker = new Worker(queue, taskCounts, taskResults);

        Thread thread = new Thread(worker);

        thread.start();
        thread.interrupt();
        thread.join();

        assertFalse(thread.isAlive());
        assertTrue(thread.isInterrupted());
    }
}
