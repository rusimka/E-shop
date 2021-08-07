package com.example.ecommerce.models.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(Long cartId) {
        super(String.format("The shopping cart %d is not present", cartId));
    }
}
