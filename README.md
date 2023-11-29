# Group Expense Calculator

## Overview

This project is a group expense calculator application. The application efficiently handles group transactions using Spring Boot Microservices and MongoDB for data storage.

## Problem Statement

Design and implement a group expense calculator application with the following features:

1. **Add Transactions:**

   - API to add a new money transaction for two or more users.
   - All users should be part of a single group.

2. **Show Owed Amounts:**

   - API to show the final amount that a user will owe to each member of the group as applicable.

3. **Show Receivable Amounts:**

   - API to show the final amount that a user will receive from other members of the group as applicable.

4. **Settle Transactions:**
   - API to settle all pending transactions between two users once the payment is done.

## Technologies Used

- **Microservices Architecture:** Implemented for scalability.
- **Spring Boot:** Framework for developing Microservices.
- **Gradle:** Build tool for managing dependencies.
- **JDK 11:** Java Development Kit version 11.
- **MongoDB:** NoSQL database for efficient data storage.
- **Swagger UI:** Implemented for comprehensive API documentation.

## How to Run

1. Clone the repository: `git clone https://github.com/Shivam-Saini-SS/group-expense-calculator.git`
2. Navigate to the project directory: `cd group-expense-calculator`
3. Run the application: `./gradlew bootRun`
4. Access the Swagger UI for API documentation: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) ,
   [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

## Feedback and Improvements

Your feedback is valuable. If you have any suggestions or improvements, please feel free to reach out.

## Acknowledgments

This project contributed to my learning journey in technologies like NoSQL databases, Microservices, and Spring Cloud.
