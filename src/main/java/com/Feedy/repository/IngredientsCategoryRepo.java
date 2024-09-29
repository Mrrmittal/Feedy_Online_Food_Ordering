/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IngredientsCategoryRepo extends JpaRepository<IngredientCategory, Long> {

    public List<IngredientCategory> findByRestaurantId(Long id);
}
