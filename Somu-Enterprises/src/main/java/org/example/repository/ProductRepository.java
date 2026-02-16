package org.example.repository;

import org.example.entity.Product;
import org.example.enums.ProductCategory;
import org.example.enums.StockStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Product Repository - Data access layer for Product entity
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find all products by category
     */
    List<Product> findByCategory(ProductCategory category);

    // Pageable variant
    Page<Product> findByCategory(ProductCategory category, Pageable pageable);

    /**
     * Find all products by stock status
     */
    List<Product> findByStockStatus(StockStatus stockStatus);

    // Pageable variant
    Page<Product> findByStockStatus(StockStatus stockStatus, Pageable pageable);

    /**
     * Find all products by name (case-insensitive)
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    // Pageable variant
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    /**
     * Find products by category and stock status
     */
    List<Product> findByCategoryAndStockStatus(ProductCategory category, StockStatus stockStatus);

    // Pageable variant
    Page<Product> findByCategoryAndStockStatus(ProductCategory category, StockStatus stockStatus, Pageable pageable);

    /**
     * Check if a product exists by name
     */
    boolean existsByName(String name);
}
