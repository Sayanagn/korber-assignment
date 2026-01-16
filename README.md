This project contains two Spring Boot microservices built using Java and Maven.
# Inventory Service
# Order Service

The project demonstrates REST APIs, database integration using H2, Liquibase migrations, and unit testing.

Technologies Used
-----------------
1. Java 17 (works with Java 8+)
2. Spring Boot 
3. Maven 
4. H2 In-memory Database 
5. Spring Data JPA 
6. Liquibase 
7. JUnit 5 & Mockito

Swagger (OpenAPI)

# Project Structure
    korber-assignment
    ├── inventory-service
    ├── order-service
    └── README.md

# Prerequisites

1. Java 17 installed
2. Maven installed
3. IntelliJ IDEA

# Get API
GET 
api url : http://localhost:8080/api/v1/inventory/get-inventory-details?productId=1001
Sample Response:
===============

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