/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{

        public Cart findByUserId(Long userId);
}
