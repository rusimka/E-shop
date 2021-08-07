package com.example.ecommerce.models.exceptions;

public class UserWithIdNotFoundException extends RuntimeException {
    public UserWithIdNotFoundException(Long userId) {
        super(String.format("User with id %d is not found", userId));

    }
}
