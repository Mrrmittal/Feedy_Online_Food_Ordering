/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.model.IngredientCategory;
import com.Feedy.model.Ingredients;
import com.Feedy.model.Restaurant;
import com.Feedy.repository.IngredientsCategoryRepo;
import com.Feedy.repository.IngredientsRepo;
import com.Feedy.service.IngredientService;
import com.Feedy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private IngredientsRepo ingredientsRepo;

    @Autowired
    private IngredientsCategoryRepo ingredientsCategoryRepo;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        return ingredientsCategoryRepo.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> optional = ingredientsCategoryRepo.findById(id);

        if (optional.isEmpty()){
            throw new Exception("Ingredient Category not found....");
        }

        return optional.get();

    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientsCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public Ingredients createIngredientItems(Long restaurantId, String name, Long ingredientCategoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(ingredientCategoryId);

        Ingredients ingredients = new Ingredients();
        ingredients.setName(name);
        ingredients.setRestaurant(restaurant);
        ingredients.setIngredientCategory(ingredientCategory);

        Ingredients ingredientItems =  ingredientsRepo.save(ingredients);
        ingredientCategory.getIngredients().add(ingredientItems);
        return ingredientItems;
    }

    @Override
    public List<Ingredients> findRestaurantIngredients(Long restaurantId) {
        return ingredientsRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public Ingredients updateStocks(Long id) throws Exception {
        Optional<Ingredients> optionalIngredients = ingredientsRepo.findById(id);

        if (optionalIngredients.isEmpty()){
            throw new Exception("Ingredients not found....");
        }
        Ingredients ingredients =  optionalIngredients.get();
        ingredients.setInStock(!ingredients.isInStock());
        return ingredientsRepo.save(ingredients);

    }
}
