# Java Learning Projects

A project-based Java learning journey from **Core Java to Spring Boot**, built through progressively challenging projects.

The goal is to learn practical software engineering concepts by building complete applications, with each project introducing new concepts and increasing in complexity.

This repository is also the foundation for developing the Java backend skills required for larger products such as **Vetigo** and **CodeIntel**.

---

## Learning Roadmap

| Status | Project                           | Focus                                                                      |
| ------ | --------------------------------- | -------------------------------------------------------------------------- |
| ✅      | **01 — CLI Calculator**           | Java basics, methods, loops, switch, exceptions                            |
| ✅      | **02 — CLI Todo**                 | Classes, objects, collections                                              |
| ✅      | **03 — Student Management**       | OOP, CRUD, `ArrayList`, layered architecture                               |
| ✅      | **04 — File Organizer**           | Java NIO, Streams, recursive file handling                                 |
| ✅      | **05 — URL Checker**              | URL validation, Java HTTP Client, file I/O, concurrency, response handling |
| ✅      | **06 — REST API**                 | Spring Boot, REST, HTTP methods, JSON, DTOs, exception handling            |
| ⏳      | **07 — PostgreSQL CRUD API**      | Spring Boot, PostgreSQL, JPA/Hibernate, persistence                        |
| ⏳      | **08 — Concurrent Worker System** | Threads, `ExecutorService`, concurrency                                    |
| ⏳      | **09 — Authentication API**       | Spring Security, JWT                                                       |
| ⏳      | **10 — Mini SaaS Backend**        | Multi-tenant architecture                                                  |
| ⏳      | **11 — Codebase Scanner**         | Code analysis, large-scale file processing                                 |

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
├── 09-authentication-api/
├── 10-mini-saas-backend/
└── 11-codebase-scanner/
```

Each project is maintained as an independent application with its own source code and README where appropriate.

---

## Projects Completed

### 01 — CLI Calculator

Practiced the fundamentals of Java programming:

* Variables and data types
* Methods
* Loops
* `switch`
* User input
* Exception handling

### 02 — CLI Todo

Introduced object-oriented programming and collections:

* Classes and objects
* Encapsulation
* `ArrayList`
* Basic application structure

### 03 — Student Management

Introduced a more structured application design:

* OOP
* CRUD operations
* `ArrayList`
* Service layer
* Searching
* Updating and deleting records
* Exception handling
* File persistence

### 04 — File Organizer

Focused on Java's file-system capabilities:

* Java NIO
* `Path`
* `Files`
* `DirectoryStream`
* Recursive directory traversal
* File categorization
* Streams

### 05 — URL Checker

A console-based URL checking application focused on networking, file processing, concurrency, and response analysis.

Key concepts:

* URL validation using `URI`
* Java `HttpClient`
* `HttpRequest`
* `HttpResponse`
* Request timeouts
* HTTP status-code categorization
* Response-time measurement
* File-based URL input
* `ExecutorService`
* `Callable`
* `Future`
* Concurrent URL checking
* Summary statistics
* Exception handling

**Status: Complete**

### 06 — REST API

A Spring Boot REST API built to understand the fundamentals of backend and RESTful application development before introducing persistence and authentication.

Key concepts:

* Spring Boot application structure
* REST controllers
* HTTP methods
* Request mapping
* Path variables
* JSON request/response handling
* DTOs
* Service-layer architecture
* Constructor-based dependency injection
* `ResponseEntity`
* HTTP status codes
* Global exception handling
* In-memory data management
* Safe collection modification
* Encapsulation
* Basic Maven project lifecycle

The project implements a complete in-memory Book API with:

* `GET /api/books`
* `GET /api/books/{id}`
* `POST /api/books`
* `PUT /api/books/{id}`
* `DELETE /api/books/{id}`

**Status: Complete**

---

## Upcoming Projects

### 07 — PostgreSQL CRUD API

The next project extends the REST API with persistent database storage.

Planned focus:

* PostgreSQL
* Database design
* JPA / Hibernate
* Repository pattern
* CRUD APIs
* Persistence
* Entity relationships

### 08 — Concurrent Worker System

A dedicated project for deeper concurrency concepts.

Planned focus:

* Threads
* `ExecutorService`
* Thread pools
* `Callable`
* `Future`
* Task processing
* Concurrency control

### 09 — Authentication API

Introduction to application security.

Planned focus:

* Spring Security
* Authentication
* Authorization
* JWT
* Password security
* Protected REST endpoints

### 10 — Mini SaaS Backend

A larger backend project combining concepts learned throughout the roadmap.

Planned focus:

* Multi-tenant architecture
* Authentication and authorization
* REST APIs
* PostgreSQL
* Database design
* Service-layer architecture
* Production-oriented backend structure

### 11 — Codebase Scanner

A larger engineering-focused project for analyzing software repositories.

Planned focus:

* Code analysis
* File-system processing
* Large-scale file traversal
* Project structure analysis
* Architecture mapping
* Documentation generation

---

## Tech Stack

### Current

* Java
* JDK 21
* Java Collections Framework
* Java NIO
* Java Streams
* Java HTTP Client
* Concurrency APIs
* Spring Boot
* Maven
* Git

### Planned

* PostgreSQL
* JPA / Hibernate
* Spring Security
* Docker
* AWS

---

## Learning Philosophy

Each project introduces a new engineering concept rather than simply repeating similar CRUD applications.

The progression is intentional:

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
Concurrency
    ↓
Spring Boot & REST
    ↓
Persistence
    ↓
Security
    ↓
Multi-Tenant Backend
    ↓
Large-Scale Code Analysis
```

The objective is to develop the ability to **understand problems, design solutions, write maintainable code, and build production-oriented backend systems** through hands-on implementation.

---

## Progress

**Completed: 6 / 11 projects**

```text
████████████░░░░░░░░ 55%
```

The roadmap will evolve as each project is completed and reviewed.

---

## Author

**Satnam Singh**

Java Backend Development Learning Journey
