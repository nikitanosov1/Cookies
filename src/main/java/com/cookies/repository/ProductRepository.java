package com.cookies.repository;

import com.cookies.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);
}
