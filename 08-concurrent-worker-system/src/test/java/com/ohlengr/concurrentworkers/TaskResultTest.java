package com.ohlengr.concurrentworkers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskResultTest {
    @Test
    public void testTaskResult() {
        TaskResult taskResult = new TaskResult("task-1","COMPLETED", null);
        assertEquals("task-1", taskResult.taskId());
        assertEquals("COMPLETED", taskResult.status());
        assertNull(taskResult.errorMessage());
    }

    @Test
    public void testTaskResultWithError() {
        TaskResult taskResult = new TaskResult("task-failure-1","FAILED", "Test task failure");
        assertEquals("task-failure-1", taskResult.taskId());
        assertEquals("FAILED", taskResult.status());
        assertEquals("Test task failure", taskResult.errorMessage());
    }
}
