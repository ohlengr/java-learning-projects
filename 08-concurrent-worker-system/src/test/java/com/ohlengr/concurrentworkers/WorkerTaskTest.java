package com.ohlengr.concurrentworkers;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkerTaskTest {

    @Test
    public void testWorkerTaskExecutesAction() {
        AtomicInteger counter = new AtomicInteger(0);
        WorkerTask task = new WorkerTask("task-1", counter::incrementAndGet);
        task.action().run();
        assertEquals(1, counter.get());
    }

    @Test
    public void testWorkerTaskActionThrowsRuntimeException() {
        WorkerTask task = new WorkerTask("task-1", () -> {
            throw new RuntimeException("Runtime error");
        });
        RuntimeException exception = assertThrows(RuntimeException.class, () -> task.action().run());
        assertEquals("Runtime error", exception.getMessage());
    }

}
