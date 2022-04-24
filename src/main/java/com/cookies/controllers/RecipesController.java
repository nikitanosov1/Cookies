package com.cookies.controllers;

import com.cookies.dto.ProductCreationDTO;
import com.cookies.entities.Product;
import com.cookies.entities.Recipe;
import com.cookies.service.ProductService;
import com.cookies.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Recipe> getListOfRecipesByListOfProductsName(@RequestParam(value="product") List<ProductCreationDTO> productDTO){
        List<Product> products = productDTO.stream()
                .map(o -> productService.findByName(o.getName()))
                .collect(Collectors.toList());
        return recipeService.getRecipeByProductsContaining(products);
    }

    @GetMapping("/search/{recipeName}")
    public List<Recipe> getListOfProductsByProductName(@PathVariable String recipeName) {
        return recipeService.getListOfRecipeByRecipeName(recipeName);
    }

    @GetMapping("/search/filter")
    public List<Recipe> getListOfProductsByProductNameAndListOfProductsName(@RequestParam(value="recipeName", required = false) String recipeName, @RequestParam(value="product", required = false) List<ProductCreationDTO> productDTO) {
        List<Product> products = null;
        if (productDTO != null) {
            products = productDTO.stream()
                    .map(o -> productService.findByName(o.getName()))
                    .collect(Collectors.toList());
        }
        return recipeService.getListOfRecipeByRecipeNameAndProductsContaining(recipeName, products);
    }
}
