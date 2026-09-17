# Project 03 — Student Management System

> **Status:** ✅ Completed

A console-based Student Management System built to practice Java OOP, collections, CRUD operations, exception handling, and layered application design.

## Concepts Learned

* Classes and objects
* Encapsulation
* Constructors
* `ArrayList`
* CRUD operations
* Searching
* Updating objects
* Deleting objects
* Sorting
* Service layer
* Console/UI layer
* Exception handling
* Input validation
* Object string representation with `toString()`

## Features

* Add student
* List all students
* Search student by ID
* Search student by name
* Partial name matching
* Update student
* Delete student
* Sort students
* Input validation
* Exception handling

## Project Structure

```text
03-student-management/
└── src/
    ├── Main.java
    ├── Student.java
    ├── StudentService.java
    └── StudentConsole.java
```

### Architecture

```text
StudentConsole
      ↓
StudentService
      ↓
   Student
```

* **Student** — Represents the student data/model.
* **StudentService** — Contains student management and business logic.
* **StudentConsole** — Handles user interaction and console input/output.

## Run

```bash
javac src/*.java
java -cp src Main
```

## Learning Outcome

This project provided hands-on practice with Java object-oriented programming and introduced a basic layered application structure.

The separation between the model, service layer, and console layer provides an early foundation for understanding the layered architecture commonly used in larger Java applications and Spring Boot projects.
