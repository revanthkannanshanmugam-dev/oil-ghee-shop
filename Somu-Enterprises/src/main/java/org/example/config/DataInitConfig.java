package org.example.config;

import org.example.entity.Product;
import org.example.enums.ProductCategory;
import org.example.enums.ProductUnit;
import org.example.enums.StockStatus;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Database Configuration - Initializes sample products on startup (Development only)
 */
@Configuration
@Profile("dev")
public class DataInitConfig {

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            // Clear existing data
            productRepository.deleteAll();

            // Create sample products - Oils
            Product sunflowerOil = new Product(
                "Sunflower Oil",
                ProductCategory.OIL,
                450.0,
                ProductUnit.LITRE,
                StockStatus.IN_STOCK,
                "https://example.com/images/sunflower-oil.jpg"
            );

            Product mustardOil = new Product(
                "Mustard Oil",
                ProductCategory.OIL,
                520.0,
                ProductUnit.LITRE,
                StockStatus.IN_STOCK,
                "https://example.com/images/mustard-oil.jpg"
            );

            Product coconutOil = new Product(
                "Coconut Oil",
                ProductCategory.OIL,
                680.0,
                ProductUnit.LITRE,
                StockStatus.OUT_OF_STOCK,
                "https://example.com/images/coconut-oil.jpg"
            );

            // Create sample products - Ghee
            Product desiGhee = new Product(
                "Desi Ghee",
                ProductCategory.GHEE,
                850.0,
                ProductUnit.KG,
                StockStatus.IN_STOCK,
                "https://example.com/images/desi-ghee.jpg"
            );

            Product buffaloGhee = new Product(
                "Buffalo Ghee",
                ProductCategory.GHEE,
                950.0,
                ProductUnit.KG,
                StockStatus.IN_STOCK,
                "https://example.com/images/buffalo-ghee.jpg"
            );

            Product pureGhee = new Product(
                "Pure Cow Ghee",
                ProductCategory.GHEE,
                1050.0,
                ProductUnit.KG,
                StockStatus.IN_STOCK,
                "https://example.com/images/cow-ghee.jpg"
            );

            // Save sample products
            productRepository.save(sunflowerOil);
            productRepository.save(mustardOil);
            productRepository.save(coconutOil);
            productRepository.save(desiGhee);
            productRepository.save(buffaloGhee);
            productRepository.save(pureGhee);

            System.out.println("✓ Sample product data initialized successfully!");
            System.out.println("✓ Created 3 oil products and 3 ghee products");
        };
    }
}


