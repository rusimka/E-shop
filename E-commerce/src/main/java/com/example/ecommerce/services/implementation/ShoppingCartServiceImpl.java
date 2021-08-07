package com.example.ecommerce.services.implementation;


import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ShoppingCart;
import com.example.ecommerce.models.User;
import com.example.ecommerce.models.enumerations.ShoppingCartStatus;
import com.example.ecommerce.models.exceptions.*;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ShoppingCartRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.services.ProductService;
import com.example.ecommerce.services.ShoppingCartService;
import com.example.ecommerce.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;





    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserService userService, ProductService productService, ProductRepository productRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.productService = productService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ShoppingCart createNewShoppingCart(String userId) {
        User user = this.userService.findById(userId);
        if (this.shoppingCartRepository.existsByUserUsernameAndShoppingCartStatus(
                user.getUsername(),
                ShoppingCartStatus.CREATED
        )) {
            throw new ShoppingCartIsAlreadyCreated(userId); // vo ovde ke se izvrsuva kodot ako fleze vo if
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setShoppingCartStatus(ShoppingCartStatus.CREATED);
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    @Transactional
    public ShoppingCart addProductToShoppingCart(String userId, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(userId);
        Product product = this.productService.findById(productId);
        for (Product product1 : shoppingCart.getProducts()) {
            if (product1.getId().equals(productId)) {
                throw new ProductIsAlreadyInShoppingCartException(product.getProductName());
            }
        }
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);

    }

    @Override
    public ShoppingCart getActiveShoppingCart(String userId) {

        if (this.shoppingCartRepository.existsByUserUsernameAndShoppingCartStatus(userId, ShoppingCartStatus.CREATED)) {
            return this.findActiveShoppingCartByUsername(userId);
        } else {
            ShoppingCart shoppingCart = new ShoppingCart();
            User user = this.userService.findByUsername(userId);
            shoppingCart.setUser(user);
            shoppingCart.setShoppingCartStatus(ShoppingCartStatus.CREATED);
            return this.shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public ShoppingCart findActiveShoppingCartByUsername(String userId) {
        return this.shoppingCartRepository.findByUserUsernameAndShoppingCartStatus(userId,ShoppingCartStatus.CREATED)
                   .orElseThrow(() -> new ShoppingCartNotActiveException(userId));
    }

    @Override
    public List<Product> getProductsInCart(Long cartId) {
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    @Transactional
    public List<Product> getCartByUserId(Long userId) {
//        ShoppingCart shoppingCart = this.shoppingCartRepository.findShoppingCartByUserId(userId);
//        List<Product> productInShoppingCart = shoppingCart.getProducts();
//        for (int i = 0; i < productInShoppingCart.size(); i++) {
//            System.out.println(productInShoppingCart.get(i).getProductName());
//        }
        return this.shoppingCartRepository.findShoppingCartByUserId(userId).getProducts();

    }

    @Override
    @Transactional
    public Long getShoppingCartId(Long userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findShoppingCartByUserId(userId);
        System.out.println(shoppingCart.getId());
        return  this.shoppingCartRepository.findShoppingCartByUserId(userId).getId();
    }



    @Override
    @Transactional
    public ShoppingCart removeProductFromShoppingCart(Long cartId, Long productId) {
      ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartId).orElseThrow(() -> new ShoppingCartNotFoundException(cartId));
      List<Product> products = shoppingCart.getProducts();
      int productIndexToRemove = products.indexOf(this.productRepository.findById(productId).get());
      products.remove(productIndexToRemove);
      shoppingCart.setProducts(products);
        return this.shoppingCartRepository.save(shoppingCart);
    }


}
