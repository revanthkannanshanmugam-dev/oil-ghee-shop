# FAQ - Product Management Backend

## Getting Started

### Q: How do I run the application?
**A:** 
```bash
mvn clean install
mvn spring-boot:run
```
The API will be available at `http://localhost:8080`

### Q: Where can I test the API?
**A:** Use any of these methods:
- **cURL** (command line)
- **Postman** (GUI application)
- **curl** in terminal
- Browser (for GET requests only)

### Q: What database does it use?
**A:** 
- **Development:** H2 in-memory database (no setup needed)
- **Production:** MySQL 8.0+
- Access H2 console: `http://localhost:8080/h2-console`

---

## Product Entity

### Q: What fields does a Product have?
**A:**
- `id` - Auto-generated unique identifier
- `name` - Product name (max 255 characters)
- `category` - OIL or GHEE
- `price` - Product price (Double)
- `unit` - LITRE or KG
- `stockStatus` - IN_STOCK or OUT_OF_STOCK
- `imageUrl` - Product image URL (optional, max 500 characters)
- `lastUpdated` - Auto-managed timestamp (read-only)

### Q: Can I manually set the lastUpdated field?
**A:** No, it's automatically managed by JPA. It's set when the product is created and updated whenever the product is modified.

### Q: What does the stockStatus field represent?
**A:** It represents whether the product is currently available for purchase:
- `IN_STOCK` - Product is available
- `OUT_OF_STOCK` - Product is not available (sold out)

### Q: Can a product have duplicate names?
**A:** No, the system checks for duplicate product names and rejects them with an error message.

---

## API Usage

### Q: How do I create a product?
**A:**
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

### Q: What are valid category values?
**A:** `OIL` or `GHEE` (case-insensitive in URLs)

### Q: What are valid unit values?
**A:** `LITRE` or `KG` (case-insensitive in URLs)

### Q: What are valid stock status values?
**A:** `IN_STOCK` or `OUT_OF_STOCK` (case-insensitive in URLs)

### Q: How do I filter products by category?
**A:**
```bash
# Get all oils
curl http://localhost:8080/api/products/category/OIL

# Get all ghee
curl http://localhost:8080/api/products/category/GHEE
```

### Q: How do I filter by stock status?
**A:**
```bash
# Get in-stock products
curl http://localhost:8080/api/products/stock/IN_STOCK

# Get out-of-stock products
curl http://localhost:8080/api/products/stock/OUT_OF_STOCK
```

### Q: How do I search for a product?
**A:**
```bash
curl "http://localhost:8080/api/products/search?name=Oil"
```
This will find all products containing "Oil" in their name (case-insensitive).

### Q: How do I update only the price?
**A:**
```bash
curl -X PATCH "http://localhost:8080/api/products/1/price?price=850.0"
```

### Q: How do I update only the stock status?
**A:**
```bash
curl -X PATCH "http://localhost:8080/api/products/1/stock-status?status=OUT_OF_STOCK"
```

### Q: What's the difference between PUT and PATCH?
**A:**
- **PUT** - Updates all fields of the product (full update)
- **PATCH** - Updates specific fields only (partial update)

### Q: What HTTP status codes are used?
**A:**
- `200` - Success (GET, PUT, DELETE, PATCH)
- `201` - Created (POST)
- `400` - Bad Request (invalid data)
- `404` - Not Found (product doesn't exist)
- `500` - Server Error

---

## Data Management

### Q: Where are sample products created?
**A:** In the `DataInitConfig.java` file, which runs automatically when the app starts in development mode.

### Q: What sample products are created?
**A:** 6 products:
1. Sunflower Oil - ₹450/L
2. Mustard Oil - ₹520/L
3. Coconut Oil - ₹680/L (out of stock)
4. Desi Ghee - ₹850/Kg
5. Buffalo Ghee - ₹950/Kg
6. Pure Cow Ghee - ₹1050/Kg

### Q: Can I disable sample data loading?
**A:** Yes, by not running the dev profile or removing the DataInitConfig class.

### Q: How do I clear all products?
**A:** Use the H2 Console and run: `DELETE FROM PRODUCTS;`

### Q: Can I export the database?
**A:** Yes, use H2 Console to export data, or use MySQL tools if using MySQL in production.

---

## API Response Format

### Q: What does a successful response look like?
**A:**
```json
{
  "status": "success",
  "message": "Products retrieved successfully",
  "data": [...]
}
```

### Q: What does an error response look like?
**A:**
```json
{
  "status": "error",
  "message": "Product not found with id: 999"
}
```

### Q: Why is there always a "data" field?
**A:** For consistency - it contains the actual result (products, product details, etc.) or `null` for operations that don't return data.

---

## Configuration & Deployment

### Q: How do I switch to MySQL for production?
**A:** 
1. Update `application-prod.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/somu_enterprises
spring.datasource.username=root
spring.datasource.password=your_password
```
2. Run with: `mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"`

### Q: How do I change the server port?
**A:** In `application.properties`, change:
```properties
server.port=8081
```

### Q: How do I enable CORS for my frontend?
**A:** CORS is already enabled in `CorsConfig.java`. It allows requests from all origins (`*`).

### Q: Can I restrict CORS to specific origins?
**A:** Yes, modify `CorsConfig.java` to specify allowed origins instead of `*`.

### Q: How do I deploy using Docker?
**A:** 
1. Build: `mvn clean package`
2. Docker: `docker build -t somu-api .`
3. Run: `docker run -p 8080:8080 somu-api`

Or use docker-compose: `docker-compose up`

---

## Troubleshooting

### Q: Port 8080 is already in use. What do I do?
**A:** Change the port in `application.properties`:
```properties
server.port=8081
```

### Q: I get "Table doesn't exist" error
**A:** Check `spring.jpa.hibernate.ddl-auto` is set to `update` or `create` in properties.

### Q: Sample data doesn't load
**A:** Make sure you're running the `dev` profile:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Q: I can't connect to H2 Console
**A:** 
1. Make sure app is running
2. Check URL: `http://localhost:8080/h2-console`
3. JDBC URL should be: `jdbc:h2:mem:testdb`
4. Username: `sa`
5. Password: (blank)

### Q: MySQL connection refused
**A:** 
1. Verify MySQL is running: `net start MySQL80` (Windows)
2. Check credentials in properties
3. Verify database exists: `CREATE DATABASE somu_enterprises;`

### Q: Build fails with dependency errors
**A:** 
```bash
mvn clean install -U
```
This clears Maven cache and downloads fresh dependencies.

### Q: JSON parsing error when creating product
**A:** Ensure:
1. Content-Type header is `application/json`
2. JSON is valid (no trailing commas)
3. All required fields are present
4. Enum values are correct (OIL, GHEE, etc.)

---

## Advanced Questions

### Q: Can I add custom validation?
**A:** Yes, add validation annotations to Product.java:
```java
@NotNull
@Min(0)
private Double price;
```

### Q: How do I add more filter methods?
**A:** Add to `ProductRepository.java`:
```java
List<Product> findByPriceGreaterThan(Double price);
```

### Q: Can I add pagination?
**A:** Yes, modify repository to extend `PagingAndSortingRepository` and use `Pageable`.

### Q: How do I add authentication?
**A:** Implement Spring Security with JWT (future enhancement).

### Q: Can I filter by multiple fields?
**A:** Yes, the `findByCategoryAndStockStatus()` method demonstrates this.

### Q: How do I add timestamps for creation and update separately?
**A:** Modify Product.java to add `createdAt` field with only `@PrePersist`.

---

## Performance & Optimization

### Q: How many products can the API handle?
**A:** Limited by database and available memory. H2 is good for ~10K records. Use MySQL for production with proper indexing.

### Q: Should I add pagination for large product lists?
**A:** Yes, for production. Add `Pageable` parameter to GET /api/products.

### Q: How can I optimize database queries?
**A:** 
1. Add indexes to frequently searched fields
2. Use `@EntityGraph` for eager loading
3. Implement caching with Spring Cache

### Q: Is the API thread-safe?
**A:** Yes, Spring Data JPA and Spring MVC handle concurrency properly.

---

## Documentation

### Q: Where can I find API documentation?
**A:** 
- `README.md` - Project overview
- `API_DOCUMENTATION.md` - Complete API reference
- `QUICK_REFERENCE.md` - Quick API reference
- `DEVELOPMENT_GUIDE.md` - Development guidelines

### Q: How do I add new endpoints?
**A:** See `DEVELOPMENT_GUIDE.md` for step-by-step instructions.

### Q: Can I generate Swagger/OpenAPI documentation?
**A:** Yes, add `springdoc-openapi` dependency and access at `/swagger-ui.html`.

---

## Support & Help

### Q: Where can I report a bug?
**A:** Check GitHub issues or contact the development team.

### Q: How do I contribute?
**A:** See DEVELOPMENT_GUIDE.md for coding standards and contribution guidelines.

### Q: Is there a roadmap?
**A:** Yes, see README.md for planned features.

---

**Last Updated:** February 2026  
**Version:** 1.0.0

For more help, check the documentation files in the project root.

