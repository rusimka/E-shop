package com.example.ecommerce.controllers;


import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return this.productService.findAll();
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping("/create-product")
    public Product saveProduct(@RequestBody @Valid Product product) throws IOException {
        return this.productService.saveProduct(product);

    }
//    @PostMapping("/products")
//    public Product saveProduct(@RequestParam("name") String name, @RequestParam("price") Float price, @RequestParam("quantity") Integer quantity,
//                               @RequestParam("description") String description, @RequestParam("image") MultipartFile image) throws IOException {
//        return this.productService.saveProduct(name,price,quantity,description,image);
//    }


//    @PutMapping("/products/{id}")
//    public Product updateProduct(@PathVariable Long id, @RequestParam(value = "name") String name, @RequestParam("price") Float price, @RequestParam("quantity") Integer quantity,
//                                 @RequestParam("description") String description, @RequestParam("image") MultipartFile image) throws IOException {
//        return this.productService.updateProduct(id,name,price,quantity,description,image);
//
//    }


    @PutMapping( value = "/products/{id}")
    public Product updateProduct(@PathVariable Long id,   @RequestBody Product product) throws IOException {
        return this.productService.updateProduct(id,product);
        // here you've had @RequestParam('image') -> it worked fine in back end but in front nope
        // MultiPartImage image
    }


    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.deleteById(id);
    }



}
