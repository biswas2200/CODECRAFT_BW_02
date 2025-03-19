# CODECRAFT_BW_02
 THIS REPOSITORY CONTAINS CODECRAFT BACKEND DEVELOPMENT TASK 02

# Task 2: Extended User Management REST API with Persistent Storage

## Overview

This task extends the functionality of the User Management REST API (Task 1) by adding persistent storage and secure configuration management. The following enhancements have been implemented:

1. **Persistent Storage:**
    - Replaced the in-memory storage (HashMap) with a relational database (MySQL).
    - Integrated Spring Data JPA for entity management and database operations.
    - Created a `users` table to store user information using Flyway migrations.

2. **Environment Variables:**
    - Used environment variables for database credentials and configuration to enhance portability and security.
    - Eliminated hardcoding of sensitive information like database username, password, and connection URL.

3. **Environment-Specific Configuration:**
    - Configured `application.properties` to dynamically use environment variables.
    - Enabled seamless deployment across multiple environments (development, testing, production) without changing application code.

---

## Features

### Existing Features (from Task 1)
- **Create, Read, Update, and Delete (CRUD) Operations** for users.
- **Validations:**
    - Email must contain an "@" symbol.
    - Age cannot be greater than 90.
- **Custom Exceptions** and Global Exception Handling.

### New Features (in Task 2)
- Integrated MySQL database for persistent data storage.
- Flyway-based database migrations.
- Secure and environment-specific configuration using IntelliJ IDEA and environment variables.

---

## Prerequisites

1. **Install MySQL:**
    - MySQL Server 8.0 or later.
    - Ensure the MySQL service is running.
    - Create a database for the API (e.g., `my_database`).

2. **Environment Variables:**
    - Set the following environment variables in your system or IntelliJ IDEA:
        - `MY_USERNAME` (MySQL username)
        - `MY_PASSWORD` (MySQL password)
        - `MY_DATASOURCE` (JDBC URL for the database, e.g., `jdbc:mysql://localhost:3306/my_database`)

3. **Java and Maven:**
    - Ensure Java 11+ and Maven are installed.

4. **MySQL Dependency:**
    - Add the following dependency to `pom.xml`:
      ```xml
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
      </dependency>
      ```

---

## Environment Configuration

### Setting Environment Variables
You can set environment variables globally or in IntelliJ IDEA:
- **Globally on Windows:**
    1. Open "Environment Variables" in system settings.
    2. Add the variables:
        - `MY_USERNAME=my_user`
        - `MY_PASSWORD=my_password`
        - `MY_DATASOURCE=jdbc:mysql://localhost:3306/my_database`

- **In IntelliJ IDEA:**
    1. Go to `Run > Edit Configurations`.
    2. Add the environment variables in the "Environment Variables" field:
       ```
       MY_USERNAME=my_user
       MY_PASSWORD=my_password
       MY_DATASOURCE=jdbc:mysql://localhost:3306/my_database
       ```

### Application Properties
The `application.properties` file dynamically references the environment variables:
```properties
spring.datasource.url=${MY_DATASOURCE}
spring.datasource.username=${MY_USERNAME}
spring.datasource.password=${MY_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
### Flyway Migration
Flyway is used to manage database migrations. Add the migration file to `src/main/resources/db/migration/`:
`V1__Create_users_table.sql`
```sql
CREATE TABLE users (
id BINARY(16) NOT NULL,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL UNIQUE,
age INT,
PRIMARY KEY (id)
);
```
**On application startup, Flyway will automatically apply this migration and create the `users` table.**

## Create a New User
- **URL:** `POST /api/users`

- **Request Body:**

```json
{
"name": "Alice",
"email": "alice@example.com",
"age": 25
}
```
- **Response:**


```json
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"name": "Alice",
"email": "alice@example.com",
"age": 25
}
```
## Retrieve All Users
- **URL: `GET /api/users`**

- **Response:**

```json
[
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"name": "Alice",
"email": "alice@example.com",
"age": 25
}
]
```
## Retrieve a User by ID
- **URL: `GET /api/users/{id}`**

- **Response:**

```json
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"name": "Alice",
"email": "alice@example.com",
"age": 25
}
```
## Update a User
- **URL: `PUT /api/users/{id}`**

- **Request Body:**

```json
{
"name": "Alice Smith",
"email": "alice.smith@example.com",
"age": 30
}
```
- **Response:**

```json
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"name": "Alice Smith",
"email": "alice.smith@example.com",
"age": 30
}
```
## Delete a User
- **URL: `DELETE /api/users/{id}`**

- **Response: 204 No Content**

### Exception Handling
**The global exception handler catches and handles custom exceptions:** 

- **UserNotFoundException: 404 Not Found**

- **BadRequestException: 400 Bad Request**

- **Example Error Response:**

```json
{
"error": "User not found with id: 123e4567-e89b-12d3-a456-426614174000"
}
```
### Summary
- **This extended API now includes:**

- **Persistent storage using MySQL.**

- **Secure and portable configuration using environment variables.**

- **Automated database migrations with Flyway.**

- **Fully functional CRUD operations with improved scalability and portability.**

- **This structure ensures a secure, robust, and production-ready solution for managing users.**

