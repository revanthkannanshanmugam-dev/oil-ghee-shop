package org.example.controller;

import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.enums.ProductCategory;
import org.example.enums.StockStatus;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Product Controller - Handles product-related API requests
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Get all products (supports pagination and sorting)
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({}) Pageable pageable) {
        try {
            Page<Product> page = productService.getAllProducts(pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Products retrieved successfully");
            response.put("data", page.getContent());
            response.put("page", page.getNumber());
            response.put("size", page.getSize());
            response.put("totalElements", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Get product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product retrieved successfully");
            response.put("data", product);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Get products by category (supports pagination and sorting)
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@PathVariable String category,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        try {
            ProductCategory prodCategory = ProductCategory.valueOf(category.toUpperCase());
            Page<Product> page = productService.getProductsByCategory(prodCategory, pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Products retrieved successfully");
            response.put("data", page.getContent());
            response.put("page", page.getNumber());
            response.put("size", page.getSize());
            response.put("totalElements", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid category. Valid categories are: OIL, GHEE");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Get products by stock status (supports pagination and sorting)
     */
    @GetMapping("/stock/{status}")
    public ResponseEntity<Map<String, Object>> getProductsByStockStatus(@PathVariable String status,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        try {
            StockStatus stockStatus = StockStatus.valueOf(status.toUpperCase());
            Page<Product> page = productService.getProductsByStockStatus(stockStatus, pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Products retrieved successfully");
            response.put("data", page.getContent());
            response.put("page", page.getNumber());
            response.put("size", page.getSize());
            response.put("totalElements", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid stock status. Valid values are: IN_STOCK, OUT_OF_STOCK");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Search products by name (supports pagination and sorting)
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String name,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        try {
            Page<Product> page = productService.searchProductsByName(name, pageable);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Products retrieved successfully");
            response.put("data", page.getContent());
            response.put("page", page.getNumber());
            response.put("size", page.getSize());
            response.put("totalElements", page.getTotalElements());
            response.put("totalPages", page.getTotalPages());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Create a new product
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.createProduct(productDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product created successfully");
            response.put("data", product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Update an existing product
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        try {
            Product product = productService.updateProduct(id, productDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product updated successfully");
            response.put("data", product);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Update product stock status
     */
    @PatchMapping("/{id}/stock-status")
    public ResponseEntity<Map<String, Object>> updateStockStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            StockStatus stockStatus = StockStatus.valueOf(status.toUpperCase());
            Product product = productService.updateStockStatus(id, stockStatus);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Stock status updated successfully");
            response.put("data", product);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid stock status. Valid values are: IN_STOCK, OUT_OF_STOCK");
        } catch (RuntimeException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Update product price
     */
    @PatchMapping("/{id}/price")
    public ResponseEntity<Map<String, Object>> updatePrice(@PathVariable Long id, @RequestParam Double price) {
        try {
            Product product = productService.updatePrice(id, price);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Price updated successfully");
            response.put("data", product);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Helper method to build error response
     */
    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }
}
