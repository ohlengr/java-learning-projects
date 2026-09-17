# Java URL Checker

A console-based URL checking application built with Java to validate URLs, perform HTTP requests, process multiple URLs, read URLs from files, and generate response statistics.

The project also includes concurrent URL checking using Java's `ExecutorService`, `Callable`, and `Future` APIs.

---

## Overview

**Java URL Checker** is a command-line application designed to check the availability and HTTP response of URLs.

The application can:

* Validate HTTP and HTTPS URLs
* Check individual URLs
* Check multiple URLs
* Read URLs from a file
* Check multiple URLs concurrently
* Handle connection and timeout failures
* Categorize HTTP responses
* Measure response times
* Generate a summary report

The project was built to strengthen practical Java skills through a small but complete application with separation between console, service, and model responsibilities.

---

## Features

### URL Validation

Validates whether a URL:

* Is not blank
* Has a valid URI structure
* Uses `http` or `https`
* Contains a valid host

### Single URL Checking

Check an individual URL and display:

* HTTP status code
* Response time

### Multiple URL Checking

Enter multiple URLs interactively and check them sequentially.

### File-Based URL Checking

Read URLs from a text file and process them automatically.

Blank lines are ignored when reading URLs from the file.

### Concurrent URL Checking

File-based URL checking uses a fixed thread pool to process multiple valid URLs concurrently.

The application uses:

* `ExecutorService`
* `Callable`
* `Future`

### HTTP Response Analysis

Responses are categorized into:

* `2xx` — Successful responses
* `3xx` — Redirects
* `4xx` — Client errors
* `5xx` — Server errors

### Response Time Statistics

The summary report calculates:

* Average response time
* Fastest URL
* Slowest URL
* Total response time

### Error Handling

The application handles:

* Invalid URLs
* Missing files
* Connection failures
* HTTP connection timeouts
* Other I/O/request failures
* Interrupted requests

---

## Concepts Practiced

This project provided practical experience with:

* Java classes and objects
* Encapsulation
* Constructors
* `final` fields
* Collections
* `ArrayList`
* Exception handling
* Checked exceptions
* `URI`
* Java HTTP Client
* `HttpClient`
* `HttpRequest`
* `HttpResponse`
* Request timeouts
* File I/O
* `Path`
* `Files`
* Java Streams
* `ExecutorService`
* `Callable`
* `Future`
* Concurrent task execution
* Thread interruption
* Response-time measurement
* Separation of responsibilities
* Basic application architecture

---

## Tech Stack

* **Language:** Java
* **JDK:** Java 21
* **HTTP Client:** Java `HttpClient`
* **Concurrency:** `ExecutorService`, `Callable`, `Future`
* **File Handling:** Java NIO
* **Build Tool:** None — standard Java project
* **Interface:** Command Line

---

## Project Structure

```text
05-url-checker/
│
├── src/
│   ├── Main.java
│   │
│   ├── console/
│   │   └── UrlCheckerConsole.java
│   │
│   ├── model/
│   │   ├── UrlCheckSummary.java
│   │   └── UrlResult.java
│   │
│   └── service/
│       └── UrlCheckerService.java
│
├── urls.txt
│
└── README.md
```

> The exact file/folder structure may vary depending on the local project configuration.

---

## Application Flow

```text
User
 │
 ▼
Main
 │
 ▼
UrlCheckerConsole
 │
 ├── Single URL
 │
 ├── Multiple URLs
 │
 └── URLs from File
          │
          ▼
   UrlCheckerService
          │
          ├── Validate URL
          │
          ├── Read File
          │
          └── HTTP Request
                    │
                    ▼
                UrlResult
                    │
                    ▼
              UrlCheckSummary
                    │
                    ▼
              Summary Report
```

---

## How It Works

### 1. URL Validation

Before making an HTTP request, the application validates the URL.

Only URLs using:

```text
http://
https://
```

with a valid host are accepted.

Invalid URLs are recorded in the summary without attempting an HTTP request.

---

### 2. HTTP Request

For valid URLs, the application creates an HTTP GET request using Java's built-in `HttpClient`.

Each request has a timeout of:

```text
5 seconds
```

Redirects are handled using:

```text
HttpClient.Redirect.NORMAL
```

The application records:

* Final response URL
* HTTP status code
* Response time

---

### 3. Concurrent Processing

When URLs are loaded from a file, valid URLs are submitted to a fixed thread pool:

```text
Thread Pool Size: 3
```

Conceptually:

```text
URL 1 ──┐
URL 2 ──┤
URL 3 ──┼──> Thread Pool
URL 4 ──┤
URL 5 ──┘
```

The application uses `Callable<UrlResult>` for HTTP requests and `Future<UrlResult>` to retrieve the results.

The executor is shut down after processing using a `finally` block to ensure proper resource cleanup.

---

## Summary Report

After URL processing, the application generates a summary containing:

```text
========== URL CHECK SUMMARY ==========
Total URLs: 5
Valid URLs: 4
Invalid URLs: 1
Successful: 3
Redirects: 0
Client Errors: 1
Server Errors: 0
Failed/Timeout: 0
Average Time: 245 ms
Fastest URL: https://example.com (120 ms)
Slowest URL: https://example.org (410 ms)
========================================
```

The actual values depend on the URLs being checked and the network response at runtime.

---

## Example Usage

### Menu

```text
Java URL Checker
=================
1. Check URL
2. Check Multiple URLs
3. Check URLs from File
4. Exit
```

### Single URL

```text
Enter URL:
https://example.com

Checking URLs...
Result for: https://example.com
Response Code: 200
Response Time: 180 ms
```

### File Input

A URL file can contain one URL per line:

```text
https://example.com
https://google.com
https://github.com
https://invalid-url
```

Blank lines are ignored.

---

## Error Handling

The application handles common request and file-related failures without terminating the entire application.

Examples include:

| Error               | Handling                               |
| ------------------- | -------------------------------------- |
| Invalid URL         | Recorded as invalid                    |
| File not found      | Displays file-not-found message        |
| Connection failure  | Recorded as failed request             |
| Connection timeout  | Recorded as failed request             |
| I/O/request failure | Recorded as failed request             |
| Interrupted request | Thread interruption status is restored |

For concurrent requests, exceptions originating from the `Callable` are handled through `ExecutionException`.

---

## Design

The project uses a simple separation of responsibilities.

### `Main`

Responsible for:

* Application startup
* Creating dependencies
* Controlling the application loop

### `UrlCheckerConsole`

Responsible for:

* Menu display
* User input
* Console output
* Coordinating URL-checking operations

### `UrlCheckerService`

Responsible for:

* URL validation
* HTTP requests
* Reading URLs from files

### `UrlResult`

Represents the result of an HTTP request:

```text
URL
Status Code
Response Time
```

### `UrlCheckSummary`

Maintains and displays aggregate statistics for a URL-checking operation.

---

## Running the Project

### Requirements

* Java Development Kit (JDK) 21 or later
* Java-compatible IDE or terminal

### Run from an IDE

Open the project in your Java IDE and run:

```text
Main.java
```

### Run from the terminal

Compile the source files according to your project structure and run the `Main` class.

---

## Future Improvements

Possible future improvements include:

* Configurable thread-pool size
* Configurable request timeout
* Export summary reports to CSV
* More detailed failure information
* Additional HTTP methods
* Retry support for transient failures
* Command-line arguments
* Unit and integration tests
* Improved console formatting

These are intentionally outside the current project scope.

---

## Project Status

**Status:** Complete

This project was built as part of a practical Java learning path focused on moving from basic Java syntax and OOP toward real-world application structure, HTTP communication, file handling, exception handling, and concurrency.

---

## Author

**Satnam Singh**

Java Backend Development Learning Path

