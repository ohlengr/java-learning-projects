# Project 06 — REST API

A Spring Boot REST API built as part of my **project-based Java learning roadmap**, progressing from Core Java toward production-oriented backend development.

This project focuses on understanding REST fundamentals, HTTP request/response handling, Spring Boot application structure, service-layer design, JSON serialization, DTOs, HTTP status codes, and exception handling before introducing persistent databases and authentication.

> **Status:** ✅ Complete

## Project Goal

Build a complete in-memory REST API using Spring Boot and understand how a backend application:

1. Receives HTTP requests
2. Maps requests to controller methods
3. Passes business operations to a service layer
4. Processes application data
5. Returns JSON responses and appropriate HTTP status codes
6. Handles application errors consistently

The project intentionally uses **in-memory storage** so the focus remains on REST and Spring Boot fundamentals.

Database persistence will be introduced in **Project 07 — PostgreSQL CRUD API**.

---

## Technologies

* Java 21
* Spring Boot 4.1.1
* Maven
* Embedded Apache Tomcat
* IntelliJ IDEA
* Git

---

## API

The project implements a simple Book REST API.

| Method   | Endpoint          | Purpose          | Success          |
| -------- | ----------------- | ---------------- | ---------------- |
| `GET`    | `/api/books`      | Get all books    | `200 OK`         |
| `GET`    | `/api/books/{id}` | Get a book by ID | `200 OK`         |
| `POST`   | `/api/books`      | Create a book    | `201 Created`    |
| `PUT`    | `/api/books/{id}` | Update a book    | `200 OK`         |
| `DELETE` | `/api/books/{id}` | Delete a book    | `204 No Content` |

For a missing book, the API returns:

```text
404 Not Found
```

with a structured JSON error response.

---

## Project Structure

```text
06_rest_api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ohlengr/restapi/
│   │   │       ├── controller/
│   │   │       │   └── BookController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   └── CreateBookRequest.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── BookNotFoundException.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── Book.java
│   │   │       │   └── ErrorResponse.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   └── BookService.java
│   │   │       │
│   │   │       └── Application.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
└── README.md
```

---

## Architecture

The application follows a simple layered structure:

```text
HTTP Request
     ↓
BookController
     ↓
BookService
     ↓
In-Memory List<Book>
     ↓
JSON Response
```

Exceptions are handled separately:

```text
BookService
     ↓
BookNotFoundException
     ↓
GlobalExceptionHandler
     ↓
404 JSON Response
```

This structure provides a foundation for introducing repositories and database persistence in the next project.

---

## Concepts Learned

### Spring Boot

* Spring Boot application structure
* Maven dependency management
* Embedded Tomcat
* Application configuration
* Application startup

### REST

* REST resource design
* HTTP methods
* Request mappings
* Path variables
* Request bodies
* JSON responses
* HTTP status codes

### Spring MVC

* `@RestController`
* `@RequestMapping`
* `@GetMapping`
* `@PostMapping`
* `@PutMapping`
* `@DeleteMapping`
* `@PathVariable`
* `@RequestBody`
* `ResponseEntity`

### Application Design

* Controller layer
* Service layer
* Constructor-based dependency injection
* DTOs
* Encapsulation
* In-memory state management

### Error Handling

* Custom runtime exceptions
* `@RestControllerAdvice`
* `@ExceptionHandler`
* Structured error responses
* Appropriate HTTP error status codes

### Java Concepts Reinforced

* `List`
* `ArrayList`
* `Iterator`
* Safe collection modification
* `Collections.unmodifiableList()`
* Mutable vs immutable object state
* Incrementing identifiers

---

## Example Request

### Create a Book

```http
POST /api/books
Content-Type: application/json
```

```json
{
  "title": "Clean Architecture",
  "author": "Robert C. Martin"
}
```

Response:

```http
201 Created
```

```json
{
  "id": 3,
  "title": "Clean Architecture",
  "author": "Robert C. Martin"
}
```

---

## Example Error Response

Requesting a book that does not exist:

```http
GET /api/books/99
```

Response:

```http
404 Not Found
```

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Book with id 99 not found"
}
```

---

## Running the Project

Clone the repository and navigate to the project:

```bash
cd 06_rest_api
```

Run the application using Maven Wrapper:

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Or run `Application` directly from IntelliJ IDEA.

The application starts on:

```text
http://localhost:8080
```

---

## Build Verification

The project can be cleaned and tested using:

### Windows

```bash
mvnw.cmd clean test
```

### Linux / macOS

```bash
./mvnw clean test
```

The project successfully passes the Maven test lifecycle.

---

## What I Learned

This project was the transition from **Core Java applications to Spring Boot backend development**.

The most important concepts were not just creating endpoints, but understanding the responsibilities of each layer:

```text
Controller
   ↓
handles HTTP

Service
   ↓
handles application logic

Model / DTO
   ↓
represents data

Exception Handler
   ↓
handles application errors
```

The project also reinforced an important REST principle: the API should communicate the result of an operation through appropriate HTTP status codes, such as `201 Created`, `204 No Content`, and `404 Not Found`.

---

## What's Next

### Project 07 — PostgreSQL CRUD API

The next project will extend the REST API with persistent storage.

Planned concepts:

* PostgreSQL
* Database design
* JPA / Hibernate
* Entities
* Repositories
* Persistence
* Database-backed CRUD APIs

The goal is to move from:

```text
REST API
    ↓
In-Memory List
```

to:

```text
REST API
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

---

## Part of the Java Learning Roadmap

This project is **Project 06 of 11** in my project-based Java learning journey.

```text
01 — CLI Calculator          ✅
02 — CLI Todo                ✅
03 — Student Management      ✅
04 — File Organizer          ✅
05 — URL Checker             ✅
06 — REST API                ✅
07 — PostgreSQL CRUD API     ⏳
08 — Concurrent Worker       ⏳
09 — Authentication API      ⏳
10 — Mini SaaS Backend       ⏳
11 — Codebase Scanner        ⏳
```

The long-term objective is to develop the skills required to design and build maintainable, production-oriented backend systems with Java and Spring Boot.
