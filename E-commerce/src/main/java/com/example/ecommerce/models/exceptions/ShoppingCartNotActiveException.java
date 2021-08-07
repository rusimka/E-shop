package com.example.ecommerce.models.exceptions;

public class ShoppingCartNotActiveException extends RuntimeException {
    public ShoppingCartNotActiveException(String userId) {
        super(String.format("The shoppping cart for the user %s is not active", userId));
    }
}
