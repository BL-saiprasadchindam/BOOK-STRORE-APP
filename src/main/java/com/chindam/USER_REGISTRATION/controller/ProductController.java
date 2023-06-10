package com.chindam.USER_REGISTRATION.controller;

import com.chindam.USER_REGISTRATION.model.Product;
import com.chindam.USER_REGISTRATION.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = repository.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
}
