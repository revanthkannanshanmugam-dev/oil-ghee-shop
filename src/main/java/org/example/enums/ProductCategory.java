package org.example.enums;

/**
 * ProductCategory Enum - Represents the type of edible product
 */
public enum ProductCategory {
    OIL("Oil"),
    GHEE("Ghee");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

