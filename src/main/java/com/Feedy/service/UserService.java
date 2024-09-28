/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.service;

import com.Feedy.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;

}
