package com.example.ecommerce.services.implementation;


import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.exceptions.ProductNotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.services.ProductService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(id));
        // mozhe i Optional ako nema vakva impl so exceptions

    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Product saveProduct(Product product) throws IOException {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setDescription(product.getDescription());
        newProduct.setImageBase64(product.getImageBase64());



//        if (image != null && !image.getName().isEmpty()) {
//            byte[] bytes = image.getBytes();
//            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
////            product.setImage(image);
//            newProduct.setImageBase64(base64Image);
//        }
        return this.productRepository.save(newProduct);
    }


//    @Override
//    public Product saveProduct(String name,Float price, Integer quantity, String description, MultipartFile image) throws IOException {
//        Product newProduct = new Product(name,price,quantity,description);
//        if (image != null && !image.getName().isEmpty()) {
//            byte[] bytes = image.getBytes();
//            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
////            product.setImage(image);
//            newProduct.setImageBase64(base64Image);
//        }
//        return this.productRepository.save(newProduct);
//    }

//    @Override
//    public Product updateProduct(Long id,String name,Float price, Integer quantity, String description, MultipartFile image) throws IOException {
//        Product productForUpdate = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
//        productForUpdate.setProductName(name);
//        productForUpdate.setPrice(price);
//        productForUpdate.setQuantity(quantity);
//        productForUpdate.setDescription(description);
//        if (image != null && !image.getName().isEmpty()) {
//            byte[] bytes = image.getBytes();
//            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
////            product.setImage(image);
//            productForUpdate.setImageBase64(base64Image);
//        }
//        return this.productRepository.save(productForUpdate);
//    }

    @Override
    public Product updateProduct(Long id, Product product) throws IOException {
        Product productForUpdate = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productForUpdate.setProductName(product.getProductName());
        productForUpdate.setPrice(product.getPrice());
        productForUpdate.setQuantity(product.getQuantity());
        productForUpdate.setDescription(product.getDescription());
        productForUpdate.setImageBase64(product.getImageBase64());
//        if (image != null && !image.getName().isEmpty()) {
//            byte[] bytes = image.getBytes();
//            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
////            product.setImage(image);
//            productForUpdate.setImageBase64(base64Image);
//        }
        return this.productRepository.save(productForUpdate);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);

    }
}
