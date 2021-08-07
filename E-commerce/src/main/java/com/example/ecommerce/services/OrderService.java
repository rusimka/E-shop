package com.example.ecommerce.services;

import com.example.ecommerce.models.Order;

public interface OrderService {

    Order createOrder(Order order);

    Order updateOrder(Order order);


}
