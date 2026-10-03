# Java Learning Projects

A project-based Java learning journey from **Core Java to Spring Boot**, built through progressively challenging projects.

The goal is to learn practical software engineering concepts by building complete applications, with each project introducing new concepts and increasing in complexity.

This repository is also the foundation for developing the Java backend skills required for larger products such as **Vetigo** and **CodeIntel**.

---

## Learning Roadmap

| Status | Project                           | Focus                                                                  |
| ------ | --------------------------------- | ---------------------------------------------------------------------- |
| ✅      | **01 — CLI Calculator**           | Java basics, methods, loops, `switch`, exceptions                      |
| ✅      | **02 — CLI Todo**                 | Classes, objects, collections                                          |
| ✅      | **03 — Student Management**       | OOP, CRUD, `ArrayList`, layered architecture                           |
| ✅      | **04 — File Organizer**           | Java NIO, Streams, recursive file handling                             |
| ✅      | **05 — URL Checker**              | HTTP Client, file I/O, concurrency, response handling                  |
| ✅      | **06 — REST API**                 | Spring Boot, REST, HTTP methods, JSON, DTOs, exception handling        |
| ✅      | **07 — PostgreSQL CRUD API**      | PostgreSQL, JPA/Hibernate, Spring Data JPA, persistence                |
| ⏳      | **08 — Concurrent Worker System** | Threads, `ExecutorService`, thread pools, concurrent task processing   |
| ⏳      | **09 — Authentication API**       | Spring Security, authentication, authorization, JWT                    |
| ⏳      | **10 — Mini SaaS Backend**        | Multi-tenant architecture, authentication, PostgreSQL                  |
| ⏳      | **11 — Codebase Scanner**         | Code analysis, large-scale file processing, project structure analysis |

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

# Projects Completed

## 01 — CLI Calculator

Practiced the fundamentals of Java programming:

* Variables and data types
* Methods
* Loops
* `switch`
* User input
* Exception handling

**Status: Complete**

---

## 02 — CLI Todo

Introduced object-oriented programming and collections:

* Classes and objects
* Encapsulation
* `ArrayList`
* Basic application structure
* User input and menu-driven interaction

**Status: Complete**

---

## 03 — Student Management

Introduced a more structured application design:

* OOP
* CRUD operations
* `ArrayList`
* Service layer
* Searching
* Updating and deleting records
* Exception handling
* File persistence
* Sorting

**Status: Complete**

---

## 04 — File Organizer

Focused on Java's file-system capabilities:

* Java NIO
* `Path`
* `Files`
* `DirectoryStream`
* Recursive directory traversal
* File categorization
* Streams
* File-system operations

**Status: Complete**

---

## 05 — URL Checker

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

The project introduced the fundamentals of concurrent task execution that will be explored in greater depth in Project 08.

**Status: Complete**

---

## 06 — REST API

A Spring Boot REST API built to understand the fundamentals of backend and RESTful application development before introducing persistent storage and authentication.

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
* Maven project lifecycle

The project implements a complete in-memory Book API with:

* `GET /api/books`
* `GET /api/books/{id}`
* `POST /api/books`
* `PUT /api/books/{id}`
* `DELETE /api/books/{id}`

**Status: Complete**

---

## 07 — PostgreSQL CRUD API

Extended the REST API by replacing in-memory storage with persistent PostgreSQL storage.

This project introduced the persistence layer and established the architecture:

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

* PostgreSQL database fundamentals
* SQL CRUD operations
* Database users and permissions
* JPA
* Hibernate ORM
* `@Entity`
* `@Table`
* `@Id`
* ID generation
* Spring Data JPA
* `JpaRepository`
* Repository layer
* Persistent CRUD operations
* DTOs
* Service-layer database operations
* Custom exceptions
* Global exception handling
* Structured error responses
* REST status codes
* Environment variables for database credentials
* Maven testing
* Application context testing

The project implements persistent Book CRUD operations:

* `GET /api/books`
* `GET /api/books/{id}`
* `POST /api/books`
* `PUT /api/books/{id}`
* `DELETE /api/books/{id}`

Database persistence is handled by PostgreSQL through JPA/Hibernate rather than an in-memory collection.

**Status: Complete**

---

# Upcoming Projects

## 08 — Concurrent Worker System

A dedicated project for deeper understanding of Java concurrency and concurrent task processing.

Project 05 introduced concurrency through URL checking. This project will go deeper into designing and managing a worker-based concurrent system.

Planned focus:

* Threads
* `ExecutorService`
* Thread pools
* `Callable`
* `Future`
* Task queues
* Worker systems
* Concurrent task processing
* Synchronization
* Graceful shutdown
* Error handling in concurrent tasks

---

## 09 — Authentication API

Introduction to application security and protected REST APIs.

Planned focus:

* Spring Security
* Authentication
* Authorization
* JWT
* Password hashing
* User management
* Roles and permissions
* Protected REST endpoints

---

## 10 — Mini SaaS Backend

A larger backend project combining concepts learned throughout the roadmap.

Planned focus:

* Multi-tenant architecture
* Authentication and authorization
* REST APIs
* PostgreSQL
* Database design
* Service-layer architecture
* Tenant isolation
* Production-oriented backend structure

---

## 11 — Codebase Scanner

A larger engineering-focused project for analyzing software repositories.

Planned focus:

* Code analysis
* File-system processing
* Large-scale file traversal
* Project structure analysis
* Source-code inspection
* Architecture mapping
* Documentation generation

This project will also provide practical foundations for the larger **CodeIntel** product direction.

---

# Tech Stack

## Current

* Java 21
* Java Collections Framework
* Java NIO
* Java Streams
* Java HTTP Client
* Java Concurrency APIs
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Git

## Planned

* Spring Security
* JWT
* Docker
* AWS

Technologies are introduced only when they are relevant to the project being built.

---

# Learning Progression

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
Spring Boot & REST
    ↓
Persistence & Databases
    ↓
Concurrency & Worker Systems
    ↓
Security
    ↓
Multi-Tenant Backend
    ↓
Large-Scale Code Analysis
```

The projects are designed to progressively move from individual programming concepts toward complete backend application architecture.

The objective is to develop the ability to:

* Understand problems
* Break problems into components
* Design application structure
* Write maintainable code
* Work with databases
* Build REST APIs
* Handle errors correctly
* Understand concurrency
* Apply security concepts
* Build production-oriented backend systems

---

# Progress

**Completed: 7 / 11 projects**

```text
██████████████░░░░░░░ 64%
```

### Completed

```text
01  CLI Calculator          ✅
02  CLI Todo                ✅
03  Student Management      ✅
04  File Organizer          ✅
05  URL Checker             ✅
06  REST API                ✅
07  PostgreSQL CRUD API     ✅
```

### Remaining

```text
08  Concurrent Worker System ⏳
09  Authentication API       ⏳
10  Mini SaaS Backend        ⏳
11  Codebase Scanner         ⏳
```

The roadmap will evolve as each project is completed and reviewed.

---

# Author

**Satnam Singh**

Java Backend Development Learning Journey
