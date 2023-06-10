package com.chindam.USER_REGISTRATION.repository;

import com.chindam.USER_REGISTRATION.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductRepository {
    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(UUID.randomUUID().toString(), "A", 56.0),
            new Product(UUID.randomUUID().toString(), "B", 80.0),
            new Product(UUID.randomUUID().toString(), "C", 100.0)
    ));

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(String productId) {
        return products.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();

    }
}
