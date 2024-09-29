/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service;

import com.Feedy.model.IngredientCategory;
import com.Feedy.model.Ingredients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IngredientService {


    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public Ingredients createIngredientItems(Long restaurantId, String name, Long ingredientCategoryId) throws Exception;

    public List<Ingredients> findRestaurantIngredients(Long restaurantId);

    public Ingredients updateStocks(Long id) throws  Exception;


}
