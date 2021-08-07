package com.example.ecommerce.services;

import com.example.ecommerce.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Optional<Product> findByName(String name);

    Product saveProduct(Product product) throws IOException;

   // Product saveProduct(String name,Float price, Integer quantity, String description, MultipartFile image) throws IOException;

    //Product updateProduct(Long id,String name,Float price, Integer quantity, String description, MultipartFile image) throws IOException;



     Product updateProduct(Long id,Product product) throws IOException;

    void deleteById(Long id);

}
