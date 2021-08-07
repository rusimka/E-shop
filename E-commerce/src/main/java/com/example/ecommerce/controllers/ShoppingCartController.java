package com.example.ecommerce.controllers;


import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ShoppingCart;
import com.example.ecommerce.services.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class ShoppingCartController {


    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PutMapping("/{userId}/{productId}")
    public void addProductToShoppingCart(@PathVariable String userId, @PathVariable Long productId) {
        shoppingCartService.addProductToShoppingCart(userId,productId);
    }
//
//    @GetMapping("/{cartId}")
//    public List<Product> getProductInShopping(@PathVariable Long cartId) {
//        return this.shoppingCartService.getProductsInCart(cartId);
//    }

    @GetMapping("/{userId}")
    public List<Product> getCartByUserId(@PathVariable Long userId) {
        return this.shoppingCartService.getCartByUserId(userId);
    }

    @GetMapping("/{userId}/get-cartId")
    public Long getShoppingCartId(@PathVariable Long userId) {
        return this.shoppingCartService.getShoppingCartId(userId);
    }

    @DeleteMapping("{cartId}/{productId}/remove-product")
    public void removeProductFromShoppingCart(@PathVariable Long cartId, @PathVariable Long productId) {
        shoppingCartService.removeProductFromShoppingCart(cartId,productId);
    }










}
