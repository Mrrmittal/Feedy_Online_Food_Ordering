/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.controller;


import com.Feedy.model.IngredientCategory;
import com.Feedy.model.Ingredients;
import com.Feedy.request.IngredientCategoryRequest;
import com.Feedy.request.IngredientItemsRequest;
import com.Feedy.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/create-category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request
            ) throws Exception {

        IngredientCategory ingredientCategoryItems = ingredientService.createIngredientCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(ingredientCategoryItems, HttpStatus.CREATED);
    }

    @PostMapping("/create-ingredients")
    public ResponseEntity<Ingredients> createIngredientItem(
            @RequestBody IngredientItemsRequest request
    ) throws Exception {

        Ingredients ingredientItems = ingredientService.createIngredientItems(
                                                request.getRestaurantId(),
                                                request.getName(),
                                                request.getCategoryId());
        return new ResponseEntity<>(ingredientItems, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/update-stock")
    public ResponseEntity<Ingredients> updateStock(
            @PathVariable Long id
    ) throws Exception {

        Ingredients ingredientItems = ingredientService.updateStocks(id);
        return new ResponseEntity<>(ingredientItems, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/get-ingredients")
    public ResponseEntity<List<Ingredients>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {

        List<Ingredients> ingredientItems = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(ingredientItems, HttpStatus.OK);
    }

    @GetMapping("/{id}/get-ingredients-category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {

        List<IngredientCategory> ingredientItems = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientItems, HttpStatus.OK);
    }
}
