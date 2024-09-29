/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.controller;

import com.Feedy.model.Order;
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
@RequestMapping("/api/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @GetMapping("/get-history/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id,
                                                       @RequestParam(required = false) String order_status,
                                                       @RequestHeader("Authorization")
                                                       String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getRestaurantOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/{id}/{order_status}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @PathVariable String order_status,
                                                       @RequestHeader("Authorization")
                                                       String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updateOrder(id, order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
