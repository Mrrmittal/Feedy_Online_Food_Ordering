/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepo extends JpaRepository<Ingredients, Long>{

    List<Ingredients> findByRestaurantId(Long id);
}
