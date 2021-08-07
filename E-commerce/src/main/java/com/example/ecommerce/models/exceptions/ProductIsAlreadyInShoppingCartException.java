package com.example.ecommerce.models.exceptions;

import javax.validation.constraints.NotNull;

public class ProductIsAlreadyInShoppingCartException extends RuntimeException {
    public ProductIsAlreadyInShoppingCartException(@NotNull String productName) {
        super(String.format("Product with name %s is already in shopping cart",productName));
    }
}
