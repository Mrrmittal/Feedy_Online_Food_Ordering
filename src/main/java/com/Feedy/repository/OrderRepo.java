/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    public List<Order> findByUserId(Long id);
    public List<Order> findByRestaurantId(Long restaurantId);
}
