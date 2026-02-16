package org.example.enums;

/**
 * StockStatus Enum - Represents the availability status of a product
 */
public enum StockStatus {
    IN_STOCK("In Stock"),
    OUT_OF_STOCK("Out of Stock");

    private final String displayName;

    StockStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

