/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.controller;


import com.Feedy.model.FoodCategory;
import com.Feedy.model.User;
import com.Feedy.service.FoodCategoryService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
public class FoodCategoryController {

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<FoodCategory> createFoodCategory(
            @RequestBody FoodCategory foodCategory,
            @RequestHeader("Authorization") String jwt
            ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        FoodCategory createdFoodCategory = foodCategoryService.createCategory(foodCategory.getName(), user.getId());

            return new ResponseEntity<>(createdFoodCategory, HttpStatus.CREATED);
    }


    @GetMapping("/restaurant")
    public ResponseEntity<List<FoodCategory>> getRestaurantCategory(
            @RequestHeader("Authorization") String jwt
            ) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<FoodCategory> createdFoodCategoryList = foodCategoryService.findCategoryByRestaurant(user.getId());

        return new ResponseEntity<>(createdFoodCategoryList, HttpStatus.CREATED);

    }



}
