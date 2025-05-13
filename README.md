# E-Commerce API

This project is a RESTful API for an e-commerce application built with Spring Boot and H2 database.

## Technologies Used

- Java 21
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Security
- H2 Database
- Maven
- BCrypt for password encoding

## Architecture

The project follows Clean Architecture principles and is organized into the following layers:

- **Domain Layer**: Contains business entities, repositories interfaces, and domain exceptions
- **Application Layer**: Contains use cases, DTOs, and mappers
- **Presentation Layer**: Contains REST controllers and exception handlers
- **Infrastructure Layer**: Contains implementations of repositories, security configurations, and adapters

## Running the Application

### Prerequisites

- Java 21 or higher
- Maven

### Steps

1. Clone the repository
2. Navigate to the project directory
3. Run the following command:
   ```
   ./mvnw spring-boot:run
   ```
4. The application will start on port 8080

## H2 Database Configuration

The application uses H2 as an in-memory database. The H2 Console is enabled and can be accessed at:

```
http://localhost:8080/h2-console
```

Connection details:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: ` ` (empty)

## Data Initialization

The application is configured to initialize the database with sample data on startup:

1. `schema.sql` creates all necessary tables
2. `data.sql` populates these tables with sample data including:
    - Users and customers
    - Product categories
    - Products
    - Orders and order items
    - Payments
    - Shopping carts

All passwords in the sample data are encoded with BCrypt and the original value is `password123`.

## Available Endpoints

### Authentication
- POST `/api/auth/login` - JWT login
- POST `/api/auth/session/login` - Session login
- POST `/api/auth/session/logout` - Session logout

### Customers
- POST `/api/customers` - Create a customer
- GET `/api/customers/{id}` - Get a customer by ID
- GET `/api/customers` - List customers with pagination
- PUT `/api/customers/{id}` - Update a customer
- DELETE `/api/customers/{id}` - Delete a customer

### Categories
- POST `/api/categories` - Create a category
- GET `/api/categories/{slug}` - Get a category by slug
- GET `/api/categories` - List categories with pagination
- GET `/api/admin/categories` - List categories in admin with pagination
- PUT `/api/categories/{slug}` - Update a category
- DELETE `/api/categories/{slug}` - Delete a category

### Products
- POST `/api/products` - Create a product
- GET `/api/products/{id}` - Get a product by ID
- GET `/api/products/slug/{slug}` - Get a product by slug
- PUT `/api/products/{id}` - Update a product
- DELETE `/api/products/{id}` - Delete a product
- GET `/api/products` - List products with pagination
- GET `/api/admin/products` - List products in admin with pagination
- GET `/api/products/csv` - Get CSV of products

### Cart
- POST `/api/cart/items` - Add an item to the cart
- GET `/api/cart/{id}` - Get a cart by ID
- DELETE `/api/cart/items/{id}` - Remove an item from the cart
- POST `/api/cart/clear` - Clear the cart

### Orders
- POST `/api/orders` - Create an order
- GET `/api/orders` - List orders with pagination