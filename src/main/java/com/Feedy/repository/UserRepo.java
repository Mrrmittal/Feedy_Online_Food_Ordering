/**
 * Author: Jatin Mittal
 * Date: 25-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    public User findByEmail(String email);
}
