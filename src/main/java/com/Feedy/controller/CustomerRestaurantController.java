/**
 * Author: Jatin Mittal
 * Date: 27-09-2024
 */

package com.Feedy.controller;

import com.Feedy.model.Restaurant;
import com.Feedy.model.User;
import com.Feedy.request.RestaurantRequest;
import com.Feedy.service.RestaurantService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class CustomerRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.findRestaurant(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @GetMapping("/searchAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String keyword
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @GetMapping("/searchAll/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add-favorites")
    public ResponseEntity<Restaurant> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
}
