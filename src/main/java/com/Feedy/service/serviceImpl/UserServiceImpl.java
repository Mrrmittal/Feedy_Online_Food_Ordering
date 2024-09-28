/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.configuration.JwtProvider;
import com.Feedy.model.User;
import com.Feedy.repository.UserRepo;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    // find user by JwtToken
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepo.findByEmail(email);
        return user;
    }


    // find user by simple email.
    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepo.findByEmail(email);
        // if user found null just throw exception
        if(user == null) throw new NullPointerException("User not found.");

        return user;
    }
}
