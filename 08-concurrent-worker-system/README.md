# 08 — Concurrent Worker System

A plain Java project demonstrating how to process tasks concurrently using multiple worker threads and a bounded queue.

The goal is to understand Java concurrency fundamentals by building a small task-processing system with safe result tracking, task failure handling, and graceful shutdown.

## Features

- Process tasks using multiple worker threads
- Use a bounded `BlockingQueue` to manage pending tasks
- Track completed and failed tasks
- Store task results using `ConcurrentHashMap`
- Handle task exceptions without stopping the worker
- Use a dedicated shutdown signal to stop workers
- Handle thread interruption and preserve interrupt status
- Verify behavior with unit tests

## Technologies

- Java 21
- Maven
- JUnit 5

## Project Structure

```text
08-concurrent-worker-system/
├── src/
│   ├── main/java/com/ohlengr/concurrentworkers/
│   │   ├── Application.java
│   │   ├── Worker.java
│   │   ├── WorkerTask.java
│   │   ├── QueueMessage.java
│   │   ├── ShutdownSignal.java
│   │   └── TaskResult.java
│   └── test/java/com/ohlengr/concurrentworkers/
│       ├── WorkerTest.java
│       ├── WorkerTaskTest.java
│       └── TaskResultTest.java
├── pom.xml
└── README.md
```

## How It Works

1. The application creates a bounded queue and starts three worker threads.
2. Tasks are added to the queue. Workers retrieve and execute tasks as they become available.
3. Each worker records whether a task completed or failed.
4. A failed task does not prevent the worker from processing subsequent tasks.
5. After submitting all tasks, the application sends a shutdown signal to each worker.
6. The main thread waits for all workers to finish using `Thread.join()`.

## Run the Application

From this project directory, run:

```bash
mvn compile
```

Then run the `Application` main class from your IDE.

## Run Tests

```bash
mvn test
```

## Concepts Practised

- `Thread` and `Runnable`
- `BlockingQueue` and `ArrayBlockingQueue`
- Producer-consumer pattern
- `ConcurrentHashMap`
- Thread interruption and `InterruptedException`
- Exception handling in concurrent tasks
- Interfaces and enums for message types
- Java `instanceof` pattern matching
- Unit testing with JUnit 5

## Current Verification

The application has been run successfully with the following results:

- **100,000** normal tasks completed
- **1** deliberately failing task recorded
- **100,001** task results stored
- All **3** worker threads stopped
- All **7** unit tests passed

## Learning Goal

Build a solid understanding of Java concurrency before progressing to more advanced backend systems and production-oriented application design.
