package com.ohlengr.concurrentworkers;

record TaskResult(String taskId, String status, String errorMessage) {
}