package org.example.dto;

import org.example.enums.ProductCategory;
import org.example.enums.ProductUnit;
import org.example.enums.StockStatus;
import java.time.LocalDateTime;

/**
 * ProductDTO - Data Transfer Object for Product
 */
public class ProductDTO {
    private Long id;
    private String name;
    private ProductCategory category;
    private Double price;
    private ProductUnit unit;
    private StockStatus stockStatus;
    private String imageUrl;
    private LocalDateTime lastUpdated;

    // Constructors
    public ProductDTO() {
    }

    public ProductDTO(String name, ProductCategory category, Double price, ProductUnit unit,
                      StockStatus stockStatus, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.unit = unit;
        this.stockStatus = stockStatus;
        this.imageUrl = imageUrl;
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
        return "ProductDTO{" +
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

