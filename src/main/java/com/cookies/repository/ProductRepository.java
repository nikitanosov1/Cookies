package com.cookies.repository;

import com.cookies.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);
    List<Product> findByNameContaining(String productName);
}
