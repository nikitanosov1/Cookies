package com.cookies.repository;

import com.cookies.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(Long id);

    // У вас предполагается поиск по названию - имеет смысл тогда создать индекс по названию,
    // или предполагается, что записей будет мало?
    List<Product> findByNameContaining(String productName);

    // Если ни один продукт не найден в БД, что вернет метод и как вызывающий код будет обрабатывать эту ситуацию
    Product findProductByName(String name);
}
