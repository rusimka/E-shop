package com.example.ecommerce.models.exceptions;

import javax.validation.constraints.NotNull;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(@NotNull String productName) {
        super(String.format("The product %s is out of stock, you can't buy it", productName));
    }
}
