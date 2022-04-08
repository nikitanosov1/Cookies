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
        System.out.println(product);
        return productService.addProduct(product);
    }

    @GetMapping("/search")
    public List<String> getListOfProductsByProductName(@RequestParam("productName") String productName){
        List<Product> products = productService.getListOfProductsByProductName(productName);
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/del")
    public void del(){
        productService.deleteAll();
    }
}