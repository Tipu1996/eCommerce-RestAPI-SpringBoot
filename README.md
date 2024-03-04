# E-Commerce API

The E-Commerce API is a Java Spring Boot application that serves as the backend for an e-commerce platform. It provides RESTful endpoints for managing items, users, shopping carts, and completed orders.

## Features

- **Item Management**: CRUD operations for managing items such as adding, retrieving, updating, and deleting items.
- **User Authentication**: Secure user authentication using JSON Web Tokens (JWT) for login and registration.
- **Shopping Cart**: Ability for users to add items to their shopping carts, update quantities, and remove items.
- **Order Management**: Tracking completed orders and managing order details.

## Technologies Used

- Java Spring Boot
- MongoDB for data storage
- JSON Web Tokens (JWT) for authentication
- Spring Security for securing endpoints
- Maven for dependency management

## Setup Instructions

1. **Clone the Repository**: `git clone <repository-url>`
2. **Configure MongoDB**: Install and configure MongoDB. Update the application properties (`application.properties` or `application.yml`) with your MongoDB connection details.
3. **Run the Application**: Navigate to the project directory and run `mvn spring-boot:run`.
4. **Access the API**: Once the application is running, you can access the API endpoints using tools like Postman or cURL.

## API Endpoints

- **GET /api/v1/items**: Retrieve all items
- **GET /api/v1/items/{id}**: Retrieve a specific item by ID
- **POST /api/v1/items**: Add a new item
- **PUT /api/v1/items/{id}**: Update an existing item
- **DELETE /api/v1/items/{id}**: Delete an item by ID


## Authentication

The API uses JWT for user authentication. Users can obtain a JWT token by logging in with their email and password.
