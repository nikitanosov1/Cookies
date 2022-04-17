package com.cookies.controllers;

import com.cookies.entities.Product;
import com.cookies.service.ProductService;
import com.cookies.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ProductService productService;
    @MockBean
    private RecipeService recipeService;

    List<Product> products;
    Product p1 = new Product(1L, "grape", "grim");
    Product p2 = new Product(2L, "pineapple", "pim");
    Product p3 = new Product(3L, "ice", "cim");
    Product p4 = new Product(4L, "sugar", "sim");

    @BeforeEach
    public void setUp() {
        products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
    }

    // testing get
    @Test
    public void getAllProducts_success() throws Exception {
        when(productService.getAll()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[2].name", is("ice")));
    }

    @Test
    public void getProductById_success() throws Exception {
        when(productService.getById(p3.getId())).thenReturn(p3);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("ice")));
    }

    // testing post
    @Test
    public void createProduct_success() throws Exception {
        Product p5 = new Product(5L, "chili", "chilim");
        when(productService.addProduct(p5)).thenReturn(p5);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(p5));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("chili")));
    }
}