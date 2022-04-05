package com.cookies.service;

import com.cookies.entities.Product;
import com.cookies.entities.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);
    List<Recipe> getAll();
    Recipe getById(Long id);
    Recipe save(Recipe recipe);
    List<Recipe> getRecipeByProductsContaining(List<Product> products);
}
