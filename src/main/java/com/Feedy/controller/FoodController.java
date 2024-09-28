/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.controller;


import com.Feedy.model.Food;
import com.Feedy.model.Restaurant;
import com.Feedy.model.User;
import com.Feedy.request.CreateFoodRequest;
import com.Feedy.response.MessageResponse;
import com.Feedy.service.FoodService;
import com.Feedy.service.RestaurantService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {


    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search-food")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization")
                                           String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foodList = foodService.searchFood(name);


        return new ResponseEntity<>(foodList, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant-food/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean veg,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization")
            String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Food> foodList = foodService.getRestaurantFood(restaurantId,veg,nonVeg,seasonal,food_category);


        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }



}
