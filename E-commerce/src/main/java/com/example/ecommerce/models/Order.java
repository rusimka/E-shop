package com.example.ecommerce.models;


import com.example.ecommerce.models.enumerations.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    @ManyToOne // edna naracka mozhe da pripagja samo na eden user
    @JoinColumn(name="username_id")
    private User user;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    // in this class should be all order details
    // price and quantity information about product should be from Shopping cart


    public Order(Long orderId, User user, List<Product> products, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.user = user;
        this.products = products;
        this.orderStatus = orderStatus;
    }

    public Order() {

    }
}
