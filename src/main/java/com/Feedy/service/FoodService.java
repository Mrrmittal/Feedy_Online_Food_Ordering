/**
 * Author: Jatin Mittal
 * Date: 28-09-2024
 */

package com.Feedy.service;

import com.Feedy.model.Food;
import com.Feedy.model.FoodCategory;
import com.Feedy.model.Restaurant;
import com.Feedy.request.CreateFoodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService{

    public Food createFood(CreateFoodRequest request, FoodCategory foodCategory, Restaurant restaurant);
    public void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVeg,
                                        boolean isNonVeg,
                                        boolean isSeasonal,
                                        String foodCategory
    );

    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateFoodAvailabilityStatus(Long foodId) throws Exception;
}
