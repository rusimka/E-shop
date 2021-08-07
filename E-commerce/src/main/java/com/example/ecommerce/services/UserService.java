package com.example.ecommerce.services;

import com.example.ecommerce.models.User;

public interface UserService {

    User findById(String username);

    User findByUsername(String username);



}
