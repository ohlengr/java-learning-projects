# Project 06 — REST API

A Spring Boot REST API built as part of my **project-based Java learning roadmap**, progressing from Core Java to production-ready backend development.

This project focuses on understanding how a real backend application is structured before introducing databases, authentication, and other advanced topics.

> **Current Status:** 🚧 Phase 1 Complete — Spring Boot Foundation

## Project Goal

Learn how a backend application receives HTTP requests, processes them, and returns JSON responses using Spring Boot.

This project intentionally avoids databases and authentication so the focus remains on REST fundamentals.

## Technologies

* Java 21
* Spring Boot 4.1.1
* Maven
* Embedded Apache Tomcat
* IntelliJ IDEA

## Current Progress

### Phase 1 — Spring Boot Foundation ✅

Completed:

* Spring Boot project initialization
* Maven project setup
* Embedded Tomcat startup
* Application running on port `8080`
* Initial project structure

## Project Structure

```text
rest-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.ohlengr.restapi
│   │   │       └── Application.java
│   │   └── resources
│   │       └── application.properties
│   └── test
├── pom.xml
└── README.md
```

## Concepts Learned

During Phase 1 I learned:

* What Spring Boot is
* What Maven does
* What Embedded Tomcat is
* How a Spring Boot application starts
* Why a backend server keeps running instead of exiting
* The purpose of `pom.xml`
* The role of `application.properties`

## Running the Project

Clone the repository and run:

```bash
./mvnw spring-boot:run
```

Or run `Application` directly from IntelliJ IDEA.

The application starts on:

```text
http://localhost:8080
```

## What's Next

Phase 2 will introduce:

* REST fundamentals
* HTTP requests and responses
* `GET` endpoints
* `@RestController`
* `@GetMapping`
* Returning JSON responses

---

This project is part of my long-term roadmap toward building production-ready backend applications with Spring Boot, PostgreSQL, Spring Security, and larger systems like **Vetigo** and **CodeIntel**.
