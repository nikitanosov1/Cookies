package com.cookies.service.impl;

import com.cookies.entities.Product;
import com.cookies.repository.ProductRepository;
import com.cookies.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getProductById(id);
        //return productRepository.findAll().stream().filter(product -> product.getId() == id).findAny().orElse(null);
    }
}
