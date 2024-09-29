/**
 * Author: Jatin Mittal
 * Date: 28-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.Food;
import com.Feedy.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {
    List<Food> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Food f WHERE f.name LIKE %:query% OR f.foodCategory.name LIKE %:query%")
    List<Food> searchFoodByQuery(@Param("query") String query);

}
