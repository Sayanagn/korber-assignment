This project contains two Spring Boot microservices built using Java and Maven.
# Inventory Service
# Order Service

The project demonstrates REST APIs, database integration using H2, Liquibase migrations, and unit testing.

# Tools and Technologies Used

1. Java 17 (works with Java 8+)
2. Spring Boot 
3. Maven 
4. H2 In-memory Database 
5. Spring Data JPA 
6. Liquibase 
7. JUnit 5 & Mockito
8. Insomnia for api testing


# Project Structure
    korber-assignment
    ├── inventory-service
    ├── order-service
    └── README.md

# Prerequisites

1. Java 17 installed
2. Maven installed
3. IntelliJ IDEA

# Build & Run Instructions

The project is developed and tested using IntelliJ IDEA.

1. Open the project in IntelliJ IDEA.
2. Reload Maven dependencies.
3. Run `InventoryServiceApplication` to start Inventory Service (port 8080).
4. Run `OrderServiceApplication` to start Order Service (port 8081).


# Get API
GET 
api url : http://localhost:8080/api/v1/inventory/get-inventory-details?productId=1001

Response:

    [
        {
            "data": {
            "batchId": 1,
            "productName": "Laptop",
            "quantity": 68,
            "expiryDate": "2026-06-25"
            }
        }
    ]

# Post API
api url : http://localhost:8081/api/v1/orders/add-orders
    
Request Body:
    
    {
        "productId": 1001,
        "quantity": 2
    }
    
    
Response:

    {
        "message": "Order placed. Inventory reserved.",
        "orderId": 11,
        "productId": 1001,
        "productName": "Laptop",
        "quantity": 5,
        "status": "PLACED"
    }

# Database & Data Setup

1. H2 in-memory database is used for both services. 
2. Database schema and initial data are loaded automatically during application startup using Liquibase changelogs. 
3. CSV files are used to preload inventory and order data.

# Testing
Unit tests are written using JUnit 5 and Mockito. Controller and service layer tests are included for both services.