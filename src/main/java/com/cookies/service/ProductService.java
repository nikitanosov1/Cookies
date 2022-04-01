package com.cookies.service;

import com.cookies.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    void deleteAll();
    Product editProduct(Product product);
    List<Product> getAll();
    Product getById(Long id);
    List<Product> getListOfProductsByProductName(String name);
}