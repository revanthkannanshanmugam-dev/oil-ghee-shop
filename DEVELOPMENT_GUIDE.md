# Development Guide

## Getting Started with Somu Enterprises Backend

This guide will help you understand the project structure and how to extend it with new features.

## Project Architecture

### Layered Architecture

```
┌─────────────────────────────────────────┐
│         Controller Layer                 │
│    (REST API Endpoints)                 │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│         Service Layer                    │
│    (Business Logic)                     │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│      Repository Layer                    │
│    (Data Access)                        │
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│        Database Layer                    │
│    (MySQL / Other)                      │
└─────────────────────────────────────────┘
```

## Current Project Structure

```
src/main/java/org/example/
├── App.java                    # Main Spring Boot Application
├── controller/
│   └── HomeController.java     # Home API endpoints
├── config/
│   ���── CorsConfig.java         # CORS configuration
└── exception/
    └── GlobalExceptionHandler.java  # Global exception handling
```

## Adding New Features

### 1. Create a New Entity

Create a new file in `src/main/java/org/example/entity/`

Example: `Product.java`

```java
package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    // Constructors
    public Product() {}

    public Product(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
```

### 2. Create a Repository

Create a new file in `src/main/java/org/example/repository/`

Example: `ProductRepository.java`

```java
package org.example.repository;

import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Add custom query methods if needed
}
```

### 3. Create a DTO

Create a new file in `src/main/java/org/example/dto/`

Example: `ProductDTO.java`

```java
package org.example.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public ProductDTO() {}

    public ProductDTO(String name, String description, Double price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
```

### 4. Create a Service

Create a new file in `src/main/java/org/example/service/`

Example: `ProductService.java`

```java
package org.example.service;

import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product(
            productDTO.getName(),
            productDTO.getDescription(),
            productDTO.getPrice(),
            productDTO.getQuantity()
        );
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
```

### 5. Create a Controller

Create a new file in `src/main/java/org/example/controller/`

Example: `ProductController.java`

```java
package org.example.controller;

import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Products retrieved successfully");
            response.put("data", products);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ProductDTO productDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product product = productService.createProduct(productDTO);
            response.put("status", "success");
            response.put("message", "Product created successfully");
            response.put("data", product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
```

## Running Tests

### Unit Tests
```bash
mvn test
```

### Specific Test Class
```bash
mvn test -Dtest=AppTest
```

## Maven Commands

### Clean and Build
```bash
mvn clean install
```

### Skip Tests
```bash
mvn clean install -DskipTests
```

### Build JAR
```bash
mvn clean package
```

### Run Specific Profile
```bash
mvn spring-boot:run
```

## Common Issues & Solutions

### Issue: "Port 8080 already in use"
**Solution:** Change port in `application.properties`
```properties
server.port=8081
```

### Issue: "Cannot resolve symbol"
**Solution:** Run Maven clean and install
```bash
mvn clean install
```

### Issue: "Build failures"
**Solution:** Clear cache and rebuild
```bash
mvn clean install -U
```

## Code Style Guidelines

1. **Naming Conventions**
   - Classes: PascalCase (e.g., `ProductController`)
   - Methods: camelCase (e.g., `getAllProducts`)
   - Constants: UPPER_SNAKE_CASE (e.g., `MAX_SIZE`)

2. **Comments**
   - Add JavaDoc for public methods
   - Use inline comments for complex logic

3. **Error Handling**
   - Always use try-catch in controllers
   - Use custom exceptions for business logic

4. **API Responses**
   - Maintain consistent response format
   - Always include status, message, and data fields

## Useful Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Maven Documentation](https://maven.apache.org/)

## Next Steps

1. Set up version control (Git)
2. Add database integration (if needed)
3. Add authentication and authorization (Spring Security)
4. Add API documentation (Swagger/SpringDoc)
5. Set up CI/CD pipeline (GitHub Actions, Jenkins)
6. Deploy to cloud (AWS, Azure, Heroku)

---

Happy coding! 🚀

