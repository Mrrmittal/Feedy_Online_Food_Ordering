/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.controller;


import com.Feedy.model.Order;
import com.Feedy.model.OrderItems;
import com.Feedy.model.User;
import com.Feedy.request.OrderRequest;
import com.Feedy.service.OrderService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest,
                                            @RequestHeader("Authorization")
                                            String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(orderRequest, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get-order-history")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization")
                                             String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
