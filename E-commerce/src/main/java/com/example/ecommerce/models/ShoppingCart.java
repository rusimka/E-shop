package com.example.ecommerce.models;

import com.example.ecommerce.models.enumerations.ShoppingCartStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shopping_carts")
@Getter
@Setter
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ShoppingCartStatus shoppingCartStatus = ShoppingCartStatus.CREATED;

    private LocalDateTime dateCreated  = LocalDateTime.now();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cart_products",
    joinColumns = @JoinColumn(name="cart_id"),
    inverseJoinColumns = @JoinColumn(name="product_id"))
    @JsonIgnore
            /* new table cart_products which contains the primary keys from both Shopping Cart and Products
               joinColumns -> (but foreignt key for in the cart_products table)
               inversedJoinColumn -> is the primary key from the inversed side(the side which is mapped by ,and that's products - the owner)
               The jointable attribute will connect to the owner side of the relationship, and the inverseJoinColumn to the other side
               The inverse or the referencing side simply maps to the owning side. - owning side is products table, and this inverse referse to owning side
            * */
    List<Product> products = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username_id",nullable = false)
    /*  the owning side of the relationship is user - Tomany
    JoinColumn  -> It simply means that our ShoppingCart  entity will have a foreign key column named username_id
     referring to the primary attribute id of our User entity.* /
     */
    @JsonIgnore
    private User user;


//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "username_id", nullable = false)
//    @JsonBackReference
//    private User user;



    public ShoppingCart(Long id, ShoppingCartStatus shoppingCartStatus, List<Product> products, User user) {
        this.id = id;
        this.shoppingCartStatus = shoppingCartStatus;
        this.products = products;
        this.user = user;
    }

    public ShoppingCart() {

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
