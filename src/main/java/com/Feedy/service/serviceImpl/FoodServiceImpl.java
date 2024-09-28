/**
 * Author: Jatin Mittal
 * Date: 28-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.model.Food;
import com.Feedy.model.FoodCategory;
import com.Feedy.model.Restaurant;
import com.Feedy.repository.FoodRepo;
import com.Feedy.repository.RestaurantRepo;
import com.Feedy.request.CreateFoodRequest;
import com.Feedy.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepo foodRepo;


    @Override
    public Food createFood(CreateFoodRequest request, FoodCategory foodCategory, Restaurant restaurant) {
        Food food = new Food();

        food.setName(request.getName());
        food.setFoodCategory(foodCategory);
        food.setPrice(request.getPrice());
        food.setDescription(request.getDepreciation());
        food.setImages(request.getImage());
        food.setRestaurant(restaurant);
        food.setIngredients(request.getIngredients());
        food.setSeasonable(request.isSeasonable());
        food.setVeg(request.isVeg());

        Food savedFood = foodRepo.save(food);
        restaurant.getFoodList().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepo.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVeg,
                                        boolean isNonVeg,
                                        boolean isSeasonal,
                                        String foodCategory) {
        List<Food> foods = foodRepo.findByRestaurantId(restaurantId);

        if(isVeg){
            foods = filterByVeg(foods, isVeg);
        }

        if(isNonVeg){
            foods = filterByNonVeg(foods, isNonVeg);
        }

        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }

        if (foodCategory!=null && !foodCategory.equals("")){
            foods=filterByCategory(foods,foodCategory);
        }
        
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonable()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> food.isVeg()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVeg(List<Food> foods, boolean isVeg) {
        return foods.stream().filter(food -> food.isVeg()==isVeg).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepo.searchFoodByQuery(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optional = foodRepo.findById(foodId);

        if (optional.isEmpty()){
            throw new Exception("Food not Exists....");
        }
        return optional.get();
    }

    @Override
    public Food updateFoodAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepo.save(food);
    }
}
