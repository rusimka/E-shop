package com.example.ecommerce.models.exceptions;

public class ShoppingCartIsAlreadyCreated extends RuntimeException {
    public ShoppingCartIsAlreadyCreated(String username) {
        super(String.format("Shopping cart for the user with username %s is already created", username));
    }
}
