package com.example.ecommerce.services.implementation;


import com.example.ecommerce.models.User;
import com.example.ecommerce.models.exceptions.UserNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String username) {
        return this.userRepository.findById(username).orElseThrow(
                () -> new UserNotFoundException(username));
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

    }
}
