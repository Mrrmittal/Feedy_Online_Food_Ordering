///**
// * Author: Jatin Mittal
// * Date: 26-09-2024
// */
//
//package com.Feedy.repository;
//
//import com.Feedy.model.Restaurant;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
//
//    @Query("SELECT r from Restaurant_table r WHERE lower(r.name) LIKE lower(concat('%', :query, '%'))"+
//            "OR lower(r.cuisineType) LIKE lower(concat('%',:query, '%'))")
//    List<Restaurant> findBySearchQuery(String query);
//
//    Restaurant findByOwnerId(Long userId);
//}
