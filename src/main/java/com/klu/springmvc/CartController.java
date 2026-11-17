package com.klu.springmvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private RestTemplate obj;

    @PostMapping
    public Map<String, Object> cart(@RequestBody Map<String, Object> cart) {

        Integer productId = (Integer) cart.get("productId");

        Map<String, Object> product = obj.getForObject(
                "http://localhost:8081/products/" + productId,
                Map.class);

        if (product.containsKey("message")) {

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Cart cannot be processed");
            response.put("reply", "Product id not available");

            return response;
        }

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Product added to cart successfully");
        response.put("cart details", cart);
        response.put("product details", product);

        return response;
    }
}