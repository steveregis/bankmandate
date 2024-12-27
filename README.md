# Mandate Management System

The **Mandate Management System** is a Java-based application built with Spring Boot and GraphQL. It manages mandates with functionality to create, update, delete, and retrieve mandates. The system includes robust error handling, retry mechanisms, and optimistic locking for concurrency control.

---

## Features

- **GraphQL API**:
  - Create a mandate
  - Update an existing mandate
  - Delete a mandate
  - Retrieve all mandates
  - Fetch a mandate by ID

- **Transaction Management**:
  - Ensures data consistency with `@Transactional`.

- **Retry Mechanisms**:
  - Automatic retries for transient failures using Spring Retry.

- **Optimistic Locking**:
  - Concurrency control via the `@Version` field.

- **H2 In-Memory Database**:
  - Quick setup and testing with in-memory storage.

---

## Project Structure

src/main/java/com/mandatesystem/ ├── domain # Entity classes (e.g., Mandate.java) ├── repository # JPA repository interfaces ├── service # Business logic and transaction management (e.g., MandateService.java) ├── graphql # GraphQL controllers and input classes └── exception # Custom exception handlers (optional)


---

## Prerequisites

1. **Java**: JDK 17 or higher.
2. **Maven**: For dependency management and project builds.
3. **IDE**: IntelliJ IDEA, Spring Tool Suite, or VS Code.

---

## Installation

1. Clone the repository:
   ```bash
   git clone <repository_url>
   cd mandate-management-system
2. Build the project:

   mvn clean install

3. Run the application:
    mvn spring-boot:run

API Endpoints
GraphQL Path:
Access the GraphQL API at: http://localhost:8080/graphql
Use GraphiQL for interactive testing: http://localhost:8080/graphiql

GraphQL Schema
Mandate Type

type Mandate {
    mandateId: ID
    mandateType: String
    effectiveDate: String
    expiryDate: String
    instructions: String
    version: Int
    lastModifiedBy: String
    lastModifiedDate: String
}
Queries
Fetch All Mandates:


query {
    getMandates {
        mandateId
        mandateType
        effectiveDate
        expiryDate
        instructions
    }
}
Fetch Mandate by ID:


query {
    getMandateById(mandateId: "123e4567-e89b-12d3-a456-426614174000") {
        mandateId
        mandateType
        effectiveDate
        expiryDate
        instructions
    }
}
Mutations
Create Mandate:


mutation {
    createMandate(
        mandateType: "Type1",
        effectiveDate: "2024-12-22",
        expiryDate: "2025-01-01",
        instructions: "Approve the budget"
    ) {
        mandateId
        mandateType
        effectiveDate
        expiryDate
        instructions
    }
}
Update Mandate:


mutation {
    updateMandate(
        mandateId: "123e4567-e89b-12d3-a456-426614174000",
        mandateType: "Updated Type",
        effectiveDate: "2024-12-23",
        expiryDate: "2025-02-01",
        instructions: "Updated instructions"
    ) {
        mandateId
        mandateType
        effectiveDate
        expiryDate
        instructions
    }
}
Delete Mandate:


mutation {
    deleteMandate(mandateId: "123e4567-e89b-12d3-a456-426614174000")
}
Configuration
application.properties

spring.application.name=mandate-management-system

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.initialization-mode=always

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.graphql.path=/graphql
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.path=/graphiql

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
Testing
Access the H2 Console:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa, Password: password
Use GraphiQL or Postman to test the GraphQL queries and mutations.

Future Improvements
Implement additional error handling and input validation.
Add pagination and filtering for getMandates.
Integrate with a persistent database like MySQL or PostgreSQL.
Contributing
Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

License
This project is licensed under the MIT License. See the LICENSE file for details.


---

### Steps to Add This File
1. Create a `README.md` file at the root of your repository.
2. Paste the content above into the file.
3. Commit the changes:
   ```bash
   git add README.md
   git commit -m "Add README with project details"
   git push
This README should provide clear, concise instructions and act as a guide for developers working on or reviewing the project. Let me know if any further adjustments are needed!