package com.cookies.service.impl;

import com.cookies.entities.Product;
import com.cookies.entities.Recipe;
import com.cookies.repository.RecipeRepository;
import com.cookies.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.saveAndFlush(recipe);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getById(Long id) {
        return recipeRepository.getRecipeById(id);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getRecipeByProductsContaining(List<Product> products) {
        return recipeRepository.findAll().stream().filter(recipe -> recipe.getProducts().containsAll(products)).collect(Collectors.toList());
    }

}