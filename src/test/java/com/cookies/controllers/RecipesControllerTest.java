package com.cookies.controllers;

import com.cookies.entities.Product;
import com.cookies.entities.Recipe;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RecipesControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private ProductService productService;
    @MockBean
    private RecipeService recipeService;

    List<Product> products1 = new ArrayList<>();
    List<Product> products2 = new ArrayList<>();
    List<Product> products3 = new ArrayList<>();
    List<Recipe> recipes = new ArrayList<>();
    Recipe r1 = new Recipe(1L, "cocktail", "so tasty!", "video", "photo", LocalDate.of(2022, 3, 1), products1);
    Recipe r2 = new Recipe(2L, "porridge", "very delicious!", "vid", "pho", LocalDate.of(2019, 11, 21), products2);
    Recipe r3 = new Recipe(3L, "jam", "best sweetness!", "v", "ph", LocalDate.of(2002, 8, 29), products3);

    @BeforeEach
    void setUp() {
        products1.add(new Product(1L, "grape", "grim"));
        products1.add(new Product(2L, "pineapple", "pim"));
        products2.add(new Product(3L, "ice", "cim"));
        products2.add(new Product(4L, "sugar", "sim"));
        products3.add(new Product(2L, "pineapple", "pim"));
        products3.add(new Product(4L, "sugar", "sim"));
        recipes.add(r1);
        recipes.add(r2);
        recipes.add(r3);
    }

    // testing get
    @Test
    public void getAllRecipes_success() throws Exception {
        when(recipeService.getAll()).thenReturn(recipes);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("jam")));
    }

    @Test
    public void getRecipeById_success() throws Exception {
        when(recipeService.getById(r3.getId())).thenReturn(r3);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("jam")));
    }

    // testing post
    @Test
    public void addRecipe_success() throws Exception {
        products1.add(new Product(5L, "image", "green"));
        Recipe r4 = new Recipe(4L, "salad", "mmm...", "vi", "po", LocalDate.of(2021, 6, 3), products1);
        when(recipeService.addRecipe(r4)).thenReturn(r4);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/recipes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(r4));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("salad")));
    }
}