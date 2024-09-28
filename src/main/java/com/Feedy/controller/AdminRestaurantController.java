///**
// * Author: Jatin Mittal
// * Date: 27-09-2024
// */
//
//package com.Feedy.controller;
//
//import com.Feedy.model.Restaurant;
//import com.Feedy.model.User;
//import com.Feedy.request.RestaurantRequest;
//import com.Feedy.response.MessageResponse;
//import com.Feedy.service.RestaurantService;
//import com.Feedy.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/admin/restaurants")
//public class AdminRestaurantController {
//
//    @Autowired
//    private RestaurantService restaurantService;
//
//    @Autowired
//    private UserService userService;
//
//
//    @PostMapping("/create")
//    public ResponseEntity<Restaurant> createRestaurant(
//            @RequestBody RestaurantRequest request,
//            @RequestHeader("Authorization") String jwt
//            ) throws Exception{
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.createRestaurant(request,user);
//
//        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/update/{id}")
//    public ResponseEntity<Restaurant> updateRestaurant(
//            @RequestBody RestaurantRequest request,
//            @RequestHeader("Authorization") String jwt,
//            @PathVariable Long id
//    ) throws Exception{
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.updateRestaurant(id,request);
//
//        return new ResponseEntity<>(restaurant, HttpStatus.OK);
//    }
//
//    @PostMapping("/delete/{id}")
//    public ResponseEntity<MessageResponse> deleteRestaurant(
//            @RequestHeader("Authorization") String jwt,
//            @PathVariable Long id
//    ) throws Exception{
//
//        User user = userService.findUserByJwtToken(jwt);
//        restaurantService.deleteRestaurant(id);
//
//        MessageResponse response = new MessageResponse();
//        response.setMessage("Restaurant Deleted Successfully");
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/{id}/status")
//    public ResponseEntity<Restaurant> updateRestaurantStatus(
//            @RequestBody RestaurantRequest request,
//            @RequestHeader("Authorization") String jwt,
//            @PathVariable Long id
//    ) throws Exception{
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.updateStatus(id);
//
//
//        return new ResponseEntity<>(restaurant, HttpStatus.OK);
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<Restaurant> findRestaurantByUserId(
//            @RequestHeader("Authorization") String jwt
//    ) throws Exception{
//
//        User user = userService.findUserByJwtToken(jwt);
//        Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
//
//
//        return new ResponseEntity<>(restaurant, HttpStatus.OK);
//    }
//
//
//
//}
