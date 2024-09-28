/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.controller;

import com.Feedy.model.User;
import com.Feedy.repository.UserRepo;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("/profile1")
    public ResponseEntity<User> findUserByEmail(@RequestParam("email") String email) throws Exception {
        User user = userService.findUserByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
