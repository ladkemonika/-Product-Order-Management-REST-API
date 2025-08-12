# -Product-Order-Management-REST-API
1. Entities

Create the following models:
Product: id, name, category, price
Customer: id, name, email, region
Order: id, orderDate, customerId, List<Product>, totalAmount, status (NEW, COMPLETED, CANCELLED)

2. API Endpoints
Resource   Method  Endpoint Description Product

POST  /api/products  Add a new product  Product

GET  /api/products  Get all products   Customer

POST  /api/customers Register a customer Order

POST /api/orders Place a new order  Order

GET /api/orders/{id} View order by ID Order

PUT /api/orders/{id}/cancel Cancel an order

 

3. Core Features

Use Spring Boot  with REST API annotations.
Use in-memory database MySQL.
Input validation using annotations (@NotNull, @Size, @Email, etc.)
Use @Service, @Repository, @Controller layers properly.
Return proper HTTP status codes and JSON responses.
Use DTOs to separate entity logic from request/response.

4. Tech Stack
Java 11+
Spring Boot
Spring Data JPA
Maven
MySQL
Lombok 

5.Deliverables
Source code (hosted on GitHub)
Postman collection
SQL fileusing MySQL
README with:
   How to run
   API descriptions
  


   





