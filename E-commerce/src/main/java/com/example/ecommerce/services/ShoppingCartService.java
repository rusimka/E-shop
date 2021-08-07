package com.example.ecommerce.services;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ShoppingCart;
import com.example.ecommerce.models.User;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCart createNewShoppingCart(String userId);

    ShoppingCart addProductToShoppingCart(String userId, Long productId);

    ShoppingCart getActiveShoppingCart(String userId);

    ShoppingCart findActiveShoppingCartByUsername(String userId);

    List<Product> getProductsInCart(Long cartId);

    List<Product> getCartByUserId(Long userId);

//    ShoppingCart findShoppingCartByIdAndUserId(Long cartId,Long userId);

    ShoppingCart removeProductFromShoppingCart(Long cartId, Long productId);

//    removeProductFromShoppingCart(String userId, Long productId);

    Long getShoppingCartId(Long userId);












}
