package com.poc.elastic_db_ms_it.service;

import com.poc.elastic_db_ms_it.model.Product;
import com.poc.elastic_db_ms_it.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService( ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
