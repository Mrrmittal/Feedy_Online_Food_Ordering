/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(r.cuisineType) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(@Param("query") String query);

    Restaurant findByOwnerId(Long userId);
}
