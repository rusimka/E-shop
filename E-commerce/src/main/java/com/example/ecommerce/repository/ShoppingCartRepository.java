package com.example.ecommerce.repository;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ShoppingCart;
import com.example.ecommerce.models.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {




    Optional<ShoppingCart> findByUserUsernameAndShoppingCartStatus(String username ,ShoppingCartStatus status );

    boolean existsByUserUsernameAndShoppingCartStatus(String username, ShoppingCartStatus status);

    //boolean existsByUserUsername(String username);

    ShoppingCart findShoppingCartByUserUsername(String username);

    ShoppingCart findShoppingCartByUserId(Long userId);

    List<Product> findShoppingCartByIdAndUserId(Long cartId, Long userId);








}
