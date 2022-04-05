package com.cookies.controllers;

import com.cookies.entities.Product;
import com.cookies.entities.Recipe;
import com.cookies.service.ProductService;
import com.cookies.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipeService recipeService;
    private final ProductService productService;

    @Autowired
    public RecipesController(RecipeService recipeService, ProductService productService) {
        this.recipeService = recipeService;
        this.productService = productService;
    }

    @PutMapping("/{recipeId}/products/{productId}")
    Recipe enrollProductToRecipe(
            @PathVariable Long recipeId,
            @PathVariable Long productId
    ){
        Recipe recipe = recipeService.getById(recipeId);
        Product product = productService.getById(productId);

        recipe.getProducts().add(product);;
        return recipeService.save(recipe);
    }

    @PostMapping("/add")
    public Recipe add(@RequestBody Recipe recipe){
        return recipeService.addRecipe(recipe);
    }

    @GetMapping()
    public List<Recipe> showAll(){
        return recipeService.getAll();
    }

    @GetMapping("/{recipeId}")
    public Recipe getRecipeById(@PathVariable Long recipeId){
        return recipeService.getById(recipeId);
    }

    @GetMapping("/search")
    public List<Recipe> getListOfRecipesByListOfProducts(@RequestBody List<Product> products){
        return recipeService.getRecipeByProductsContaining(products);
    }
}
