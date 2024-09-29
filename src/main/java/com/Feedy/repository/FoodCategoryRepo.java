/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepo extends JpaRepository<FoodCategory, Long> {

    public List<FoodCategory> findByRestaurantId(Long id);

}
