# 🏦 Banking System API

A RESTful banking application built with **Spring Boot** that provides core banking operations through HTTP endpoints. The system supports multiple account types with polymorphic behavior and complete CRUD operations.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen?style=flat-square&logo=spring)
![H2 Database](https://img.shields.io/badge/Database-H2-blue?style=flat-square)
![Maven](https://img.shields.io/badge/Build-Maven-red?style=flat-square&logo=apache-maven)

## 🎯 Project Overview

This banking system demonstrates modern Java backend development practices using **Spring Boot**, **JPA/Hibernate**, and **REST API** design. The application showcases object-oriented programming principles including inheritance, polymorphism, and clean architecture patterns.

### Key Features

- RESTful API - Complete HTTP endpoints for banking operations
- Multiple Account Types - Savings, Checking, and Business accounts
- Polymorphic Design - Each account type with unique business logic
- Database Integration - JPA/Hibernate with H2 database
- Clean Architecture - Layered design (Controller → Service → Repository)
- Exception Handling - Custom exceptions for business logic errors
- Dependency Injection - Spring's IoC container for component management

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controller    │ -> │     Service     │ -> │   Repository    │ -> │    Database     │
│   (REST API)    │    │ (Business Logic)│    │ (Data Access)   │    │   (H2 Memory)   │
└─────────────────┘    └─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Layer Responsibilities

- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Implements business logic and validation
- **Repository Layer**: Manages data persistence operations
- **Entity Layer**: Defines data models and relationships

## 🚀 Technologies Used

| Technology | Purpose | Version |
|------------|---------|---------|
| **Java** | Core programming language | 17 |
| **Spring Boot** | Application framework | 3.5.0 |
| **Spring Data JPA** | Data persistence | Included |
| **Hibernate** | ORM implementation | Included |
| **H2 Database** | In-memory database | Runtime |
| **Maven** | Dependency management | 4.0.0 |

## Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Application

1. **Clone the repository**
   ```bash
   git clone https://github.com/JinderO/banking-system-api.git
   cd banking-system-api
   ```

2. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

3. **Access the API**
   - Base URL: `http://localhost:8080`
   - Health check: `http://localhost:8080/`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:bankingdb`
     - Username: `sa`
     - Password: (empty)

## 📋 API Endpoints

### Account Management

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `GET` | `/api/accounts` | Get all accounts | None |
| `GET` | `/api/accounts/{id}` | Get account by ID | `id` (path) |
| `POST` | `/api/accounts` | Create new account | `type`, `number`, `owner`, `balance` (query) |

### Banking Operations

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| `POST` | `/api/accounts/{id}/deposit` | Deposit money | `id` (path), `amount` (query) |
| `POST` | `/api/accounts/{id}/withdraw` | Withdraw money | `id` (path), `amount` (query) |

### Example Requests

**Create a Savings Account:**
```http
POST /api/accounts?type=SAVINGS&number=SAV001&owner=John Doe&balance=1000.0
```

**Deposit Money:**
```http
POST /api/accounts/1/deposit?amount=500.0
```

**Get All Accounts:**
```http
GET /api/accounts
```

## Account Types

The system supports three types of accounts with different behaviors:

### Savings Account
- **Interest Rate**: 2.0% annually
- **Monthly Fees**: None
- **Purpose**: Long-term savings

### Checking Account  
- **Interest Rate**: 0.5% annually
- **Monthly Fees**: 50.00 CZK
- **Purpose**: Daily transactions

### Business Account
- **Interest Rate**: 1.0% annually  
- **Monthly Fees**: 200.00 CZK
- **Purpose**: Business operations

## 🗄️ Database Design

The application uses **Single Table Inheritance** strategy:

```sql
CREATE TABLE accounts (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_type    VARCHAR(255),  -- Discriminator column
    account_number  VARCHAR(255),
    owner_name      VARCHAR(255),
    balance         DOUBLE
);
```

Account types are distinguished by the `account_type` column:
- `SAVINGS` - Savings Account
- `CHECKING` - Checking Account  
- `BUSINESS` - Business Account

## Testing the API

You can test the API using tools like **Postman**, **curl**, or any HTTP client:

### Create Account Example
```bash
curl -X POST "http://localhost:8080/api/accounts?type=SAVINGS&number=12345&owner=Jane Smith&balance=2000"
```

### Deposit Example
```bash
curl -X POST "http://localhost:8080/api/accounts/1/deposit?amount=300"
```

### Get All Accounts Example
```bash
curl -X GET "http://localhost:8080/api/accounts"
```

## Design Patterns Used

- **Repository Pattern** - Data access abstraction
- **Service Layer Pattern** - Business logic separation  
- **Factory Pattern** - Account creation logic
- **Template Method Pattern** - Abstract account behavior
- **Dependency Injection** - Loose coupling between components

## 🛠️ Development Features

- **Auto-configuration** - Spring Boot handles setup
- **Hot reload** - Spring Boot DevTools for development
- **SQL logging** - See generated queries in console
- **H2 Console** - Web-based database management
- **Exception handling** - Custom business exceptions

## Project Structure

```
src/main/java/com/jindero/banking/
├── controller/          # REST API controllers
│   ├── BankController.java
│   └── TestController.java
├── service/            # Business logic layer
│   └── BankService.java
├── repository/         # Data access layer
│   └── AccountRepository.java
├── entity/             # JPA entities
│   ├── Account.java
│   ├── SavingsAccount.java
│   ├── CheckingAccount.java
│   ├── BusinessAccount.java
│   └── Chargeable.java
├── exception/          # Custom exceptions
│   ├── AccountNotFoundException.java
│   └── InsufficientFundsException.java
└── BankingSystemApiApplication.java
```

## Future Enhancements

- Authentication & Authorization (Spring Security)
- Input Validation (Bean Validation)
- Exception Handling (@ControllerAdvice)
- Unit Tests (JUnit 5, Mockito)
- API Documentation (Swagger/OpenAPI)
- Transaction History (Audit logging)
- Account Transfer (Between accounts)
- Rate Limiting (API protection)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Technical Achievements

- **Advanced OOP Understanding** - Demonstrates sophisticated inheritance patterns and polymorphic behavior
- **Clean Business Logic** - Professional implementation of financial domain rules and validations  
- **Enterprise Architecture** - Layered design following industry best practices for maintainability
- **Professional Documentation** - Comprehensive code organization and technical communication
- **Scalable Foundation** - Robust groundwork for enterprise banking application development

- ## 👨‍💻 Author

**Jindřich Ovádek** - *Java Developer in Training* - [@JinderO](https://github.com/JinderO)

This project showcases **enterprise-level object-oriented design patterns** commonly used in financial software development, with a focus on **extensibility**, **maintainability**, and **robust business logic implementation**. The implementation demonstrates readiness for **junior-level Java development** positions in **enterprise environments**.
