/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.model.FoodCategory;
import com.Feedy.model.Restaurant;
import com.Feedy.repository.FoodCategoryRepo;
import com.Feedy.service.FoodCategoryService;
import com.Feedy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private FoodCategoryRepo foodCategoryRepo;

    @Override
    public FoodCategory createCategory(String name, Long id) throws Exception{
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setName(name);
        foodCategory.setRestaurant(restaurant);

        return foodCategoryRepo.save(foodCategory);
    }

    @Override
    public List<FoodCategory> findCategoryByRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(restaurantId);
        return foodCategoryRepo.findByRestaurantId(restaurant.getId());
    }

    @Override
    public FoodCategory findCategory(Long id) throws Exception {
        Optional<FoodCategory> optional = foodCategoryRepo.findById(id);

        if (optional.isEmpty()){
            throw  new Exception("Category not found");
        }
        return optional.get();
    }
}
