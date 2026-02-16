package org.example.service;

import org.example.dto.ProductDTO;
import org.example.entity.Product;
import org.example.enums.ProductCategory;
import org.example.enums.StockStatus;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Product Service - Business logic for Product operations
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Get all products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get all products with pagination and sorting
     */
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Get product by ID
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Get all products by category
     */
    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    /**
     * Get products by category with pagination
     */
    public Page<Product> getProductsByCategory(ProductCategory category, Pageable pageable) {
        return productRepository.findByCategory(category, pageable);
    }

    /**
     * Get all products by stock status
     */
    public List<Product> getProductsByStockStatus(StockStatus stockStatus) {
        return productRepository.findByStockStatus(stockStatus);
    }

    /**
     * Get products by stock status with pagination
     */
    public Page<Product> getProductsByStockStatus(StockStatus stockStatus, Pageable pageable) {
        return productRepository.findByStockStatus(stockStatus, pageable);
    }

    /**
     * Search products by name
     */
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Search products by name with pagination
     */
    public Page<Product> searchProductsByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    /**
     * Get products by category and stock status
     */
    public List<Product> getProductsByCategoryAndStockStatus(ProductCategory category, StockStatus stockStatus) {
        return productRepository.findByCategoryAndStockStatus(category, stockStatus);
    }

    /**
     * Get products by category and stock status with pagination
     */
    public Page<Product> getProductsByCategoryAndStockStatus(ProductCategory category, StockStatus stockStatus, Pageable pageable) {
        return productRepository.findByCategoryAndStockStatus(category, stockStatus, pageable);
    }

    /**
     * Create a new product
     */
    public Product createProduct(ProductDTO productDTO) {
        // Check if product with same name already exists
        if (productRepository.existsByName(productDTO.getName())) {
            throw new RuntimeException("Product with name '" + productDTO.getName() + "' already exists");
        }

        Product product = new Product(
            productDTO.getName(),
            productDTO.getCategory(),
            productDTO.getPrice(),
            productDTO.getUnit(),
            productDTO.getStockStatus(),
            productDTO.getImageUrl()
        );

        return productRepository.save(product);
    }

    /**
     * Update an existing product
     */
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setUnit(productDTO.getUnit());
        product.setStockStatus(productDTO.getStockStatus());
        product.setImageUrl(productDTO.getImageUrl());

        return productRepository.save(product);
    }

    /**
     * Update product stock status
     */
    public Product updateStockStatus(Long id, StockStatus stockStatus) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setStockStatus(stockStatus);
        return productRepository.save(product);
    }

    /**
     * Update product price
     */
    public Product updatePrice(Long id, Double price) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setPrice(price);
        return productRepository.save(product);
    }
}
