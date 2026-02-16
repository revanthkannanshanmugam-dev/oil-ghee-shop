package org.example.entity;

import jakarta.persistence.*;
import org.example.enums.ProductCategory;
import org.example.enums.ProductUnit;
import org.example.enums.StockStatus;
import java.time.LocalDateTime;

/**
 * Product Entity - Represents a product in the edible oil and ghee shop
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductUnit unit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockStatus stockStatus;

    @Column(length = 500)
    private String imageUrl;

    @Column(nullable = false, updatable = true)
    private LocalDateTime lastUpdated;

    // Constructors
    public Product() {
    }

    public Product(String name, ProductCategory category, Double price, ProductUnit unit,
                   StockStatus stockStatus, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.unit = unit;
        this.stockStatus = stockStatus;
        this.imageUrl = imageUrl;
        this.lastUpdated = LocalDateTime.now();
    }

    // JPA Lifecycle Callback - Auto-update lastUpdated on persist and update
    @PrePersist
    protected void onCreate() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductUnit getUnit() {
        return unit;
    }

    public void setUnit(ProductUnit unit) {
        this.unit = unit;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", unit=" + unit +
                ", stockStatus=" + stockStatus +
                ", imageUrl='" + imageUrl + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}

