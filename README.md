# Somu Enterprises - Edible Oil & Ghee Shop Backend

A modern Spring Boot REST API backend for managing an edible oil and ghee e-commerce shop. Built with Java 17, Spring Boot 3.2, Spring Data JPA, and MySQL/H2 databases.

## Project Overview

This backend API provides complete product management functionality for an online retail shop specializing in edible oils and ghee products. It features:
- Product catalog management (oils and ghee)
- Inventory/stock status tracking
- Price management
- Advanced search and filtering
- Category-based organization

## Project Structure

```
src/
├── main/
│   ├── java/org/example/
│   │   ├── App.java                          # Main Spring Boot Application
│   │   ├── controller/
│   │   │   ├── HomeController.java           # Home API endpoints
│   │   │   └── ProductController.java        # Product management endpoints
│   │   ├── service/
│   │   │   └── ProductService.java           # Business logic for products
│   │   ├── entity/
│   │   │   └── Product.java                  # Product JPA entity
│   │   ├── repository/
│   │   │   └── ProductRepository.java        # Data access layer
│   │   ├── dto/
│   │   │   └── ProductDTO.java               # Data transfer object
│   │   ├── enums/
│   │   │   ├── ProductCategory.java          # OIL, GHEE
│   │   │   ├── ProductUnit.java              # LITRE, KG
│   │   │   └── StockStatus.java              # IN_STOCK, OUT_OF_STOCK
│   │   ├── config/
│   │   │   ├── CorsConfig.java               # CORS configuration
│   │   │   └── DataInitConfig.java           # Sample data initialization
│   │   └── exception/
│   │       └── GlobalExceptionHandler.java   # Global exception handling
│   └── resources/
│       └── application.properties             # Application configuration
└── test/
    └── java/org/example/
        └── AppTest.java                      # Integration tests
```

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.2.0
- **Spring Data JPA**: For ORM and database operations
- **MySQL**: Production database
- **H2**: In-memory database for development
- **Maven**: Build tool

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0+ (optional, for production)

## Installation & Setup

### 1. Clone/Download the Project
```bash
cd Somu-Enterprises
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run the Application

#### Option A: Using Maven
```bash
mvn spring-boot:run
```

#### Option B: Using IDE
1. Open the project in your IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Right-click on `App.java` and select "Run"

#### Option C: Build and Run JAR
```bash
mvn clean package
java -jar target/somu-enterprises-1.0.0.jar
```

### 4. Access the Application

Once running, the application will be available at:
- **API Base URL**: `http://localhost:8080`
- **H2 Console** (Development): `http://localhost:8080/h2-console`
  - Username: `sa`
  - Password: (leave blank)

## API Endpoints

### Home Endpoints
```
GET  /                    - Welcome message
GET  /api/health          - Health check
```

### Product Endpoints

#### Get All Products
```
GET /api/products
```

Response:
```json
{
  "status": "success",
  "message": "Products retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Sunflower Oil",
      "category": "OIL",
      "price": 450.0,
      "unit": "LITRE",
      "stockStatus": "IN_STOCK",
      "imageUrl": "https://example.com/images/sunflower-oil.jpg",
      "lastUpdated": "2026-02-16T10:30:00"
    }
  ]
}
```

#### Get Product by ID
```
GET /api/products/{id}
```

#### Get Products by Category
```
GET /api/products/category/{category}
```
Valid categories: `OIL`, `GHEE`

#### Get Products by Stock Status
```
GET /api/products/stock/{status}
```
Valid statuses: `IN_STOCK`, `OUT_OF_STOCK`

#### Search Products by Name
```
GET /api/products/search?name={searchTerm}
```

#### Create a New Product
```
POST /api/products
Content-Type: application/json

{
  "name": "Olive Oil",
  "category": "OIL",
  "price": 750.0,
  "unit": "LITRE",
  "stockStatus": "IN_STOCK",
  "imageUrl": "https://example.com/images/olive-oil.jpg"
}
```

#### Update a Product
```
PUT /api/products/{id}
Content-Type: application/json

{
  "name": "Extra Virgin Olive Oil",
  "category": "OIL",
  "price": 850.0,
  "unit": "LITRE",
  "stockStatus": "IN_STOCK",
  "imageUrl": "https://example.com/images/ev-olive-oil.jpg"
}
```

#### Delete a Product
```
DELETE /api/products/{id}
```

#### Update Product Stock Status
```
PATCH /api/products/{id}/stock-status?status={status}
```
Valid statuses: `IN_STOCK`, `OUT_OF_STOCK`

#### Update Product Price
```
PATCH /api/products/{id}/price?price={newPrice}
```

## Product Entity Fields

| Field | Type | Description | Constraints |
|-------|------|-------------|-------------|
| `id` | Long | Unique product identifier | Auto-generated |
| `name` | String | Product name | Required, max 255 chars |
| `category` | Enum | Product type | OIL or GHEE |
| `price` | Double | Product price | Required |
| `unit` | Enum | Unit of measurement | LITRE or KG |
| `stockStatus` | Enum | Availability status | IN_STOCK or OUT_OF_STOCK |
| `imageUrl` | String | Product image URL | Optional, max 500 chars |
| `lastUpdated` | LocalDateTime | Last modification timestamp | Auto-managed |

## Sample Data

On startup (development profile), the application initializes with sample products:

### Oils:
- Sunflower Oil - 450/L
- Mustard Oil - 520/L
- Coconut Oil - 680/L (Out of Stock)

### Ghee:
- Desi Ghee - 850/Kg
- Buffalo Ghee - 950/Kg
- Pure Cow Ghee - 1050/Kg

Access H2 Console to view data: `http://localhost:8080/h2-console`

## Configuration

### Development Profile (H2 Database)
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Production Profile (MySQL Database)

Update `src/main/resources/application-prod.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/somu_enterprises
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
```

## Features

✅ **Complete CRUD Operations**: Create, Read, Update, Delete products  
✅ **Advanced Search**: Search by name, category, and stock status  
✅ **Inventory Management**: Track stock status in real-time  
✅ **Price Management**: Easy price updates via PATCH endpoint  
✅ **Automatic Timestamps**: lastUpdated automatically managed by JPA  
✅ **Error Handling**: Consistent error responses across API  
✅ **CORS Enabled**: Ready for frontend integration  
✅ **RESTful API**: Standards-compliant REST endpoints  
✅ **JPA/Hibernate**: Efficient database operations  
✅ **H2 & MySQL Support**: Development and production ready  

## Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=AppTest
```

## Example API Calls

### Using cURL

**Create a product:**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Olive Oil",
    "category": "OIL",
    "price": 750.0,
    "unit": "LITRE",
    "stockStatus": "IN_STOCK",
    "imageUrl": "https://example.com/olive-oil.jpg"
  }'
```

**Get all products:**
```bash
curl http://localhost:8080/api/products
```

**Search for products:**
```bash
curl "http://localhost:8080/api/products/search?name=oil"
```

**Get oils only:**
```bash
curl http://localhost:8080/api/products/category/oil
```

**Get in-stock products:**
```bash
curl http://localhost:8080/api/products/stock/in_stock
```

**Update price:**
```bash
curl -X PATCH "http://localhost:8080/api/products/1/price?price=500.0"
```

**Update stock status:**
```bash
curl -X PATCH "http://localhost:8080/api/products/1/stock-status?status=out_of_stock"
```

## Troubleshooting

### Port 8080 Already in Use
Change port in `application.properties`:
```properties
server.port=8081
```

### Database Connection Issues
1. Verify MySQL is running (if using MySQL)
2. Check database credentials in `application-prod.properties`
3. For H2, no setup needed - it's in-memory

### Build Failures
```bash
mvn clean install -U
```

## Future Enhancements

- [ ] Authentication & Authorization (JWT)
- [ ] Customer/Order Management
- [ ] Payment Integration
- [ ] Email Notifications
- [ ] API Documentation (Swagger/OpenAPI)
- [ ] Advanced Analytics
- [ ] Discount/Coupon System
- [ ] Review & Rating System
- [ ] Inventory Alerts
- [ ] Mobile App Support

## Support

For issues or questions, please contact the development team.

## License

© 2026 Somu Enterprises. All rights reserved.

---

**Happy coding!** 🚀

