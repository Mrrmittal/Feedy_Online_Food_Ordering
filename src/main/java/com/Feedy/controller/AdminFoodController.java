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

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping("/create")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization")
                                           String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        Food food = foodService.createFood(request,request.getFoodCategory(),restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{
            User user = userService.findUserByJwtToken(jwt);
            foodService.deleteFood(id);

            MessageResponse response =new MessageResponse();
            response.setMessage("Food deleted Successfully");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization")
                                                      String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateFoodAvailabilityStatus(id);


        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
