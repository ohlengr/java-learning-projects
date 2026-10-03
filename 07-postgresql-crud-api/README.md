# 07 — PostgreSQL CRUD API

A Spring Boot REST API that persists books in **PostgreSQL** using **Spring Data JPA and Hibernate**.

This project extends the previous REST API project by replacing the in-memory `List<Book>` with a real relational database.

The main goal is to understand how a Spring Boot application connects the HTTP/API layer to persistent database storage.

---

## Learning Objectives

This project focuses on:

* PostgreSQL database fundamentals
* SQL CRUD operations
* JPA entities
* Hibernate ORM
* Spring Data JPA
* `JpaRepository`
* Entity ID generation
* Repository layer
* Service layer
* DTOs
* Exception handling
* REST API status codes
* PostgreSQL datasource configuration
* Environment variables for database credentials
* Maven testing
* Controller → Service → Repository architecture

---

## Technology Stack

| Technology      | Version / Details             |
| --------------- | ----------------------------- |
| Java            | 21                            |
| Spring Boot     | 4.1.1                         |
| Maven           | Maven Wrapper                 |
| PostgreSQL      | 16+                           |
| Spring Data JPA | Included with Spring Boot     |
| Hibernate       | Included with Spring Data JPA |
| REST            | Spring Web MVC                |
| Database Driver | PostgreSQL JDBC Driver        |
| IDE             | IntelliJ IDEA                 |

---

## Architecture

```text
Client
  │
  │ HTTP Request
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
Spring Data JPA
  │
  ▼
Hibernate
  │
  ▼
JDBC
  │
  ▼
PostgreSQL
```

### Responsibilities

**Controller**

Handles HTTP requests and responses.

**Service**

Contains application/business logic.

**Repository**

Provides database access through Spring Data JPA.

**Entity**

Represents persistent database data.

**DTO**

Represents request data received from the API.

---

## Project Structure

```text
07-postgresql-crud-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ohlengr/
│   │   │           └── postgresqlcrudapi/
│   │   │               ├── Application.java
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   └── BookController.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   ├── CreateBookRequest.java
│   │   │               │   └── UpdateBookRequest.java
│   │   │               │
│   │   │               ├── exception/
│   │   │               │   ├── BookNotFoundException.java
│   │   │               │   ├── ErrorResponse.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               │
│   │   │               ├── model/
│   │   │               │   └── Book.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── BookRepository.java
│   │   │               │
│   │   │               └── service/
│   │   │                   └── BookService.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── ohlengr/
│                   └── postgresqlcrudapi/
│                       └── ApplicationTests.java
│
├── .env
├── .gitignore
├── pom.xml
└── mvnw
```

---

## Database

Database:

```text
bookstore
```

Table:

```sql
CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);
```

Example data:

```text
id | title              | author
---+--------------------+-------------------
1  | Clean Architecture | Robert C. Martin
```

---

## Database Configuration

The application uses environment variables for the database password instead of storing credentials directly in source code.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=bookstore_app
spring.datasource.password=${DB_PASSWORD}
```

The local password is kept outside the source code.

`.env` is excluded from Git.

---

## JPA Entity

The `Book` class is mapped to the PostgreSQL `books` table using JPA:

```java
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    protected Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
```

`GenerationType.IDENTITY` allows PostgreSQL to generate the ID when a new book is inserted.

---

## Repository

Spring Data JPA provides the repository implementation automatically.

```java
public interface BookRepository extends JpaRepository<Book, Long> {
}
```

This provides operations such as:

```text
findAll()
findById()
save()
delete()
```

without manually writing SQL for basic CRUD operations.

---

## REST API

Base URL:

```text
/api/books
```

### Get all books

```http
GET /api/books
```

### Get a book by ID

```http
GET /api/books/{id}
```

Example:

```http
GET /api/books/1
```

### Create a book

```http
POST /api/books
```

Request:

```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch"
}
```

Response:

```text
201 Created
```

The response includes a `Location` header pointing to the newly created resource.

### Update a book

```http
PUT /api/books/{id}
```

Request:

```json
{
  "title": "Effective Java, 3rd Edition",
  "author": "Joshua Bloch"
}
```

### Delete a book

```http
DELETE /api/books/{id}
```

Response:

```text
204 No Content
```

---

## Error Handling

The application uses a custom exception:

```java
BookNotFoundException
```

and a global exception handler:

```java
@RestControllerAdvice
```

A request for a non-existent book returns:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Book not found with id: 999"
}
```

---

## Running the Application

Make sure PostgreSQL is running and the `bookstore` database is available.

Set the database password in your local environment.

### Windows PowerShell

```powershell
$env:DB_PASSWORD = "your-database-password"
```

Then run:

```powershell
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

## Running Tests

Run:

```powershell
./mvnw test
```

The project should finish with:

```text
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

The test verifies that the Spring Boot application context can start successfully with JPA, Hibernate, and PostgreSQL configured.

---

## What I Learned

### 1. JPA

JPA provides the standard API for persistence in Java applications.

### 2. Hibernate

Hibernate is the ORM implementation used by the application to translate between Java objects and relational database data.

### 3. Entity

A JPA entity represents persistent data.

```text
Book object
     ↕
books table
```

### 4. Spring Data JPA

Spring Data JPA provides repository abstractions that remove the need to implement common CRUD database operations manually.

### 5. Repository Pattern

Database access is separated from the service layer:

```text
Service → Repository → Database
```

### 6. DTOs

Request DTOs separate incoming API data from the persistence entity.

```text
CreateBookRequest
UpdateBookRequest
        ↓
      Book
        ↓
   PostgreSQL
```

### 7. Environment Variables

Sensitive database credentials should not be hard-coded into source code or committed to Git.

### 8. REST Status Codes

The API uses appropriate HTTP status codes:

```text
200 OK
201 Created
204 No Content
404 Not Found
```

---

## Key Difference From Project 06

### Project 06 — REST API

```text
Controller
    ↓
Service
    ↓
List<Book>
```

Data existed only in application memory.

### Project 07 — PostgreSQL CRUD API

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
JPA / Hibernate
    ↓
PostgreSQL
```

Data is now persisted in a real relational database.

This is the first project in the learning roadmap where the Spring Boot application works with **persistent database storage**.