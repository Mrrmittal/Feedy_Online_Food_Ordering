/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItems, Long> {
}
