package org.example.enums;

/**
 * ProductUnit Enum - Represents the unit of measurement for products
 */
public enum ProductUnit {
    LITRE("Litre", "L"),
    KG("Kilogram", "Kg");

    private final String displayName;
    private final String abbreviation;

    ProductUnit(String displayName, String abbreviation) {
        this.displayName = displayName;
        this.abbreviation = abbreviation;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}

