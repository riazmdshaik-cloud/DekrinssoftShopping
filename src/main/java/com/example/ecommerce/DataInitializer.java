// src/main/java/com/example/ecommerce/DataInitializer.java
package com.example.ecommerce;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Add sample products
        productRepository.save(new Product("Laptop", "High-performance laptop", new BigDecimal("999.99"), 10));
        productRepository.save(new Product("Smartphone", "Latest smartphone", new BigDecimal("699.99"), 25));
        productRepository.save(new Product("Headphones", "Wireless headphones", new BigDecimal("149.99"), 50));
        productRepository.save(new Product("Tablet", "10-inch tablet", new BigDecimal("399.99"), 15));
    }
}
