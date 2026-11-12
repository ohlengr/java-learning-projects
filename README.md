# Java Learning Projects

A project-based Java learning journey from **Core Java to Spring Boot**, built through progressively challenging projects.

The goal is to develop practical software engineering skills by building complete applications, with each project introducing new concepts and increasing in complexity.

This repository focuses on learning Java, backend development, application architecture, databases, concurrency, and security through hands-on implementation.

---

## Learning Roadmap

| Status | Project | Focus |
|---|---|---|
| ✅ | **01 — CLI Calculator** | Java basics, methods, loops, `switch`, exceptions |
| ✅ | **02 — CLI Todo** | Classes, objects, collections |
| ✅ | **03 — Student Management** | OOP, CRUD, `ArrayList`, layered architecture |
| ✅ | **04 — File Organizer** | Java NIO, Streams, recursive file handling |
| ✅ | **05 — URL Checker** | HTTP Client, file I/O, concurrency, response handling |
| ✅ | **06 — REST API** | Spring Boot, REST, HTTP methods, JSON, DTOs, exception handling |
| ✅ | **07 — PostgreSQL CRUD API** | PostgreSQL, JPA/Hibernate, Spring Data JPA, persistence |
| ✅ | **08 — Concurrent Worker System** | Threads, `Runnable`, bounded queues, thread-safe data, worker lifecycle |
| ✅ | **09 — Authentication API** | Spring Security, authentication, authorization, JWT |

---

## Repository Structure

```text
java-learning-projects/
├── 01-cli-calculator/
├── 02-cli-todo/
├── 03-student-management/
├── 04-file-organizer/
├── 05-url-checker/
├── 06-rest-api/
├── 07-postgresql-crud-api/
├── 08-concurrent-worker-system/
└── 09-authentication-api/
```

Each project is maintained as an independent application with its own source code and README where appropriate.

---

# Projects Completed

## 01 — CLI Calculator

Practiced the fundamentals of Java programming:

- Variables and data types
- Methods
- Loops
- `switch`
- User input
- Exception handling

**Status: Complete**

---

## 02 — CLI Todo

Introduced object-oriented programming and collections:

- Classes and objects
- Encapsulation
- `ArrayList`
- Basic application structure
- User input and menu-driven interaction

**Status: Complete**

---

## 03 — Student Management

Introduced a more structured application design:

- Object-oriented programming
- CRUD operations
- `ArrayList`
- Service-layer design
- Searching, updating, and deleting records
- Exception handling
- File persistence
- Sorting

**Status: Complete**

---

## 04 — File Organizer

Focused on Java's file-system capabilities:

- Java NIO
- `Path` and `Files`
- `DirectoryStream`
- Recursive directory traversal
- File categorization
- Streams
- File-system operations

**Status: Complete**

---

## 05 — URL Checker

A console-based URL-checking application focused on networking, file processing, concurrency, and response analysis.

Key concepts:

- URL validation using `URI`
- Java `HttpClient`
- `HttpRequest` and `HttpResponse`
- Request timeouts
- HTTP status-code categorization
- Response-time measurement
- File-based URL input
- `ExecutorService`
- `Callable` and `Future`
- Concurrent URL checking
- Summary statistics
- Exception handling

This project introduced concurrent task execution, which was explored further in Project 08.

**Status: Complete**

---

## 06 — REST API

A Spring Boot REST API built to understand backend development and RESTful application design before introducing persistent storage and authentication.

Key concepts:

- Spring Boot application structure
- REST controllers
- HTTP methods and request mapping
- Path variables
- JSON request/response handling
- DTOs
- Service-layer architecture
- Constructor-based dependency injection
- `ResponseEntity`
- HTTP status codes
- Global exception handling
- In-memory data management
- Safe collection modification
- Encapsulation
- Maven project lifecycle

The project implements an in-memory Book API:

- `GET /api/books`
- `GET /api/books/{id}`
- `POST /api/books`
- `PUT /api/books/{id}`
- `DELETE /api/books/{id}`

**Status: Complete**

---

## 07 — PostgreSQL CRUD API

Extended the REST API by replacing in-memory storage with persistent PostgreSQL storage.

The project introduced the persistence layer:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Spring Data JPA
    ↓
Hibernate
    ↓
PostgreSQL
```

Key concepts:

- PostgreSQL fundamentals and SQL CRUD operations
- Database users and permissions
- JPA and Hibernate ORM
- `@Entity`, `@Table`, and `@Id`
- ID generation
- Spring Data JPA and `JpaRepository`
- Persistent CRUD operations
- DTOs
- Service-layer database operations
- Custom exceptions
- Global exception handling
- Structured error responses
- REST status codes
- Environment variables for database credentials
- Maven testing
- Application context testing

The project implements persistent Book CRUD operations using PostgreSQL through JPA/Hibernate rather than an in-memory collection.

**Status: Complete**

---

## 08 — Concurrent Worker System

Built a Java worker system that processes tasks through a bounded queue using multiple worker threads.

The application processes **100,000 successful tasks and one deliberately failing task**, stores task results, and shuts down its workers using dedicated shutdown signals.

Key concepts:

- `Thread` and `Runnable`
- `BlockingQueue`
- Bounded queues using `ArrayBlockingQueue`
- Multiple worker threads
- `ConcurrentHashMap`
- Thread-safe counter updates using `merge()`
- Task execution and failure handling
- Dedicated queue-message types
- `QueueMessage` interface
- `ShutdownSignal` enum
- Graceful worker shutdown
- Thread interruption
- `InterruptedException`
- JUnit 5 unit testing
- Maven testing and build verification

The queue accepts both normal tasks and shutdown messages through a shared interface. Each worker processes tasks until it receives a shutdown signal or is interrupted. A failed task is recorded without stopping the worker from processing subsequent tasks.

**Verification:**

- 100,000 successful tasks processed
- 1 failed task recorded correctly
- 100,001 task results stored
- All 3 workers stopped successfully
- All 7 unit tests passed

**Status: Complete**

---

## 09 — Authentication API

Built a RESTful authentication API using Spring Boot, Spring Security, PostgreSQL, and JWT.

The project demonstrates how applications authenticate users and protect resources using stateless token-based authentication.

Key concepts:

- Spring Security configuration
- Authentication and authorization
- User registration and login
- Password hashing using BCrypt
- Email normalization
- Duplicate email handling
- PostgreSQL user persistence
- Spring Data JPA
- JWT generation using Nimbus
- HS256 token signing
- JWT signature and expiration validation
- Stateless authentication
- Protected REST endpoints
- Global exception handling
- HTTP security status codes
- Integration testing with MockMvc

Implemented endpoints:

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/protected`

JWT access tokens expire after 15 minutes.

**Verification:**

- Application context test passed
- Missing JWT correctly returns `401 Unauthorized`
- Invalid JWT correctly returns `401 Unauthorized`
- Valid JWT successfully accesses the protected endpoint with `200 OK`

**Status: Complete**

---

---

# Tech Stack

## Current

- Java 21
- Java Collections Framework
- Java NIO
- Java Streams
- Java HTTP Client
- Java Concurrency APIs
- Spring Boot
- Spring Web MVC
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT
- Maven
- Git
- JUnit 5
- Spring Boot Test
- MockMvc

## Planned

- Docker
- AWS

Technologies are introduced when they are relevant to the project being built.

---

# Learning Progression

Each project introduces new engineering concepts rather than simply repeating similar CRUD applications.

```text
Core Java
    ↓
OOP & Collections
    ↓
Application Structure
    ↓
File System & Streams
    ↓
HTTP & Networking
    ↓
Spring Boot & REST
    ↓
Persistence & Databases
    ↓
Concurrency & Worker Systems
    ↓
Spring Security & JWT
```

The projects progressively move from individual programming concepts toward complete backend application architecture.

The objective is to develop the ability to:

- Understand problems
- Break problems into components
- Design application structure
- Write maintainable code
- Work with databases
- Build REST APIs
- Handle errors correctly
- Understand concurrency
- Apply security concepts
- Build production-oriented backend systems

---

# Related Projects

The following are independent software products developed in separate repositories. They are mentioned here for context only and are not part of this repository's nine-project learning roadmap:

### Vetigo

A SaaS platform for salon management, with a focus on backend architecture, tenant management, and business application functionality.

### CodeIntel

An open-source codebase intelligence platform focused on repository scanning, code analysis, and software architecture understanding.

Both products have separate repositories and their own documentation. They are not part of the numbered Java learning projects in this repository.

---

# Progress

**Completed: 9 / 9 projects — 100%**

```text
████████████████████ 100%
```

### Completed

```text
01  CLI Calculator           ✅
02  CLI Todo                 ✅
03  Student Management       ✅
04  File Organizer           ✅
05  URL Checker              ✅
06  REST API                 ✅
07  PostgreSQL CRUD API      ✅
08  Concurrent Worker System ✅
09  Authentication API       ✅
```


The roadmap will evolve as each project is completed and reviewed.

---

# Author

**Satnam Singh**

Java Backend Development Learning Journey