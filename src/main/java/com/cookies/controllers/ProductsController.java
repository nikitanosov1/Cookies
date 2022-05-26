package com.cookies.controllers;

import com.cookies.entities.Product;
import com.cookies.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product showById(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @GetMapping()
    public List<Product> showAll(){
        return productService.getAll();
    }

    @PostMapping("/add")
    public Product add(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/search")
    public List<String> getListOfProductsByProductName(@RequestParam("productName") String productName){
        List<Product> products = productService.getListOfProductsByProductName(productName);
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    // GET - это метод, работающий только на чтение, а тут он все продукты из БД удаляет
    // Тут нужно использовать DELETE, но этот метод должен удалять сущность по id, а не все
    /*@DeleteMapping("/del/{id}")
    public void del(@PathVariable("id") Long id){
        productService.delete();
    }*/

    @GetMapping("/id")
    public Long getProductIdByProductName(@RequestParam("productName") String productName){
        Product product = productService.findByName(productName);
        if (product != null) {
            return product.getId();
        }
        return null;
    }

}