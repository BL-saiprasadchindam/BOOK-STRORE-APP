package com.chindam.USER_REGISTRATION.controller;

import com.chindam.USER_REGISTRATION.binding.UserId;
import com.chindam.USER_REGISTRATION.model.Product;
import com.chindam.USER_REGISTRATION.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController("/api")
public class CardController {
//
    private Map<String, List<Product>> cardMap = new HashMap<>();

    @Autowired
    private ProductRepository repository;

    @PostMapping("card/{productId}")
    public ResponseEntity<String> addToCard(@PathVariable String productId,
                                            @RequestBody UserId userId) {

        Optional<Product> product = repository.getProductById(productId);

        if(cardMap.containsKey(userId.getId())) {
            List<Product> products = cardMap.get(userId.getId());
            product.ifPresent(products::add);
        } else {
            List<Product> list = new ArrayList<>();
            product.ifPresent(list::add);
            cardMap.put(userId.getId(), list);
        }

        return new ResponseEntity<>("added to card" + userId.getId(), HttpStatus.OK);
    }

    @DeleteMapping("card/{productId}")
    public ResponseEntity<String> deleteProductFromCard(@PathVariable String productId,
                                                        @RequestBody String userId) {

        if(cardMap.containsKey(userId)) {
            List<Product> products = cardMap.get(userId);
            Optional<Product> optionalProduct = products.stream()
                    .filter(product -> product.getProductId().equals(productId))
                    .findFirst();
            optionalProduct.ifPresent(product -> {
                products.remove(product);
                cardMap.put(userId,products);
            });
        }
        return new ResponseEntity<>("deleted from card", HttpStatus.OK);
    }

    @GetMapping("/card/{userId}")
    public List<Product> getAllCardProducts(@PathVariable String userId) {

        List<Product> products = cardMap.get(userId);
        System.out.println("111111111111111111111111111111"+ userId);
        System.out.println(products.size());
        return products;
    }

}
