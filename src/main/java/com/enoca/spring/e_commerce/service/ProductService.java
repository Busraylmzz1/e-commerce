package com.enoca.spring.e_commerce.service;

import com.enoca.spring.e_commerce.entity.Product;
import com.enoca.spring.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        }
        return null;
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
