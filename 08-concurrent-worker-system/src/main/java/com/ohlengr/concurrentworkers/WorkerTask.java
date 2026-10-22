package com.ohlengr.concurrentworkers;

record WorkerTask(String taskId, Runnable action) {
}
