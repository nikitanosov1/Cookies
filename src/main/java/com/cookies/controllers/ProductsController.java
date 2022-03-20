package com.cookies.controllers;

import com.cookies.entities.Product;
import com.cookies.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@CrossOrigin(maxAge = 3600)
@RestController
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String index(){
        return "hello";
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

    @GetMapping("/del")
    public void del(){
        productService.deleteAll();
    }
}