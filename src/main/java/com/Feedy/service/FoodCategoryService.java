/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service;

import com.Feedy.model.FoodCategory;
import com.Feedy.repository.FoodCategoryRepo;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodCategoryService {

    public FoodCategory createCategory(String name, Long id) throws Exception;
    public List<FoodCategory> findCategoryByRestaurant(Long restaurantId) throws Exception;
    public FoodCategory findCategory(Long id) throws Exception;


}
