# 09 - Authentication API

A RESTful authentication API built with Java and Spring Boot. This project demonstrates user registration, secure password storage, login authentication, JWT generation, and protected API endpoints.

## Features

- User registration with input validation
- User login with email and password
- Email normalization (trim and lowercase)
- Password hashing using BCrypt
- Duplicate email prevention
- JWT access token generation
- JWT signature and expiration validation
- Stateless authentication using Spring Security
- Protected API endpoints
- Global exception handling
- Automated security tests

## Technology Stack

- **Java:** 21
- **Spring Boot:** 4.1.2
- **Spring Security:** 7.1.2
- **Spring Data JPA**
- **PostgreSQL:** 16
- **JWT:** Nimbus JOSE + JWT
- **Build Tool:** Maven
- **Testing:** JUnit 5, Spring Boot Test, MockMvc

## Project Structure

```text
09-authentication-api/
├── src/
│   ├── main/
│   │   ├── java/com/ohlengr/__authentication_api/
│   │   │   ├── auth/
│   │   │   ├── config/
│   │   │   ├── exception/
│   │   │   ├── jwt/
│   │   │   ├── user/
│   │   │   └── Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       └── resources/
│           └── application-test.properties
└── pom.xml
```

## Database Setup

This project uses PostgreSQL.

Create the database:

```sql
CREATE DATABASE authentication_api;
```

Configure the database connection using environment variables.

## Environment Configuration

The application requires the following environment variables:

```properties
DB_URL=jdbc:postgresql://localhost:5432/authentication_api
DB_USERNAME=postgres
DB_PASSWORD=your_database_password
JWT_SECRET=your_base64_encoded_secret
```

`JWT_SECRET` must be a Base64-encoded secret suitable for HS256 signing.

Keep production credentials and secrets outside source control. Never commit actual production secrets.

### Test Configuration

Automated tests run using the Spring `test` profile.

The test profile uses:

```text
src/test/resources/application-test.properties
```

This file contains a dedicated test-only JWT secret.

The test secret is separate from the production secret and must never be reused in production.

## API Endpoints

Base URL:

```text
http://localhost:8080
```

### 1. Register User

**POST** `/api/auth/register`

Request:

```json
{
  "name": "Test User",
  "email": "test@example.com",
  "password": "password123"
}
```

Successful response:

```json
{
  "id": 1,
  "name": "Test User",
  "email": "test@example.com"
}
```

Duplicate email returns `409 Conflict`.

### 2. Login

**POST** `/api/auth/login`

Request:

```json
{
  "email": "test@example.com",
  "password": "password123"
}
```

Successful response:

```json
{
  "id": 1,
  "name": "Test User",
  "email": "test@example.com",
  "token": "your.jwt.token"
}
```

Invalid credentials return `401 Unauthorized`.

The generated JWT expires after 15 minutes.

### 3. Access Protected Endpoint

**GET** `/api/protected`

Include the JWT in the Authorization header:

```http
Authorization: Bearer your.jwt.token
```

Successful response:

```text
You are authenticated!
```

Requests without a valid JWT return `401 Unauthorized`.

## Running the Application

Make sure PostgreSQL is running and the required environment variables are configured.

Run using Maven Wrapper:

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

**Windows**

```bash
mvnw.cmd spring-boot:run
```

The application starts at:

```text
http://localhost:8080
```

## Running Tests

Execute the automated test suite:

**Linux / macOS**

```bash
./mvnw clean test
```

**Windows**

```bash
mvnw.cmd clean test
```

The automated tests cover:

- Application context startup
- Protected endpoint access without a JWT
- Protected endpoint access with an invalid JWT
- Protected endpoint access with a valid JWT

## Security Implementation

- Passwords are hashed using BCrypt.
- Plain-text passwords are never returned in API responses.
- JWTs are signed using HS256.
- JWT expiration is set to 15 minutes.
- Spring Security validates JWT signatures and expiration.
- Authentication is stateless.
- Authentication endpoints are publicly accessible.
- Other endpoints require a valid JWT.

## Learning Objectives

This project demonstrates practical understanding of:

- Spring Security configuration
- Authentication and authorization
- Password encoding and verification
- JWT generation and validation
- Stateless REST API security
- Global exception handling
- Integration testing using MockMvc

---

**Project 09 completed:** Authentication API using Java, Spring Boot, PostgreSQL, Spring Security, and JWT.