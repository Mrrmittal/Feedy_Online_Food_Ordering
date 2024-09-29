/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.controller;

import com.Feedy.model.Cart;
import com.Feedy.model.CartItem;
import com.Feedy.model.Food;
import com.Feedy.model.User;
import com.Feedy.request.AddCartItemRequest;
import com.Feedy.request.UpdateCartItemRequest;
import com.Feedy.service.CartService;
import com.Feedy.service.FoodService;
import com.Feedy.service.RestaurantService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

        @Autowired
        private CartService cartService;

        @Autowired
        private UserService userService;

        @Autowired
        private RestaurantService restaurantService;


        @GetMapping("/add-item")
        public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request,
                                                      @RequestHeader("Authorization")
                                                     String jwt) throws Exception {
            CartItem cartItem = cartService.addItemToCart(request,jwt);


            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }

        @PostMapping("/update")
        public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request,
                                                               @RequestHeader("Authorization")
                                                               String jwt) throws Exception {

            CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());


            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }

        @PostMapping("/remove/{id}")
        public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                               @RequestHeader("Authorization")
                                               String jwt) throws Exception {

            Cart cartItem = cartService.removeItemFromCart(id, jwt);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        }

        @PostMapping("/delete-cart")
        public ResponseEntity<Cart> removeCart(@RequestHeader("Authorization")
                                               String jwt) throws Exception {
            User user = userService.findUserByJwtToken(jwt);
            Cart cart = cartService.clearCart(user.getId());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }

        @GetMapping("/search-cart")
        public ResponseEntity<Cart> searchUserCart(@RequestHeader("Authorization")
                                               String jwt) throws Exception {
            User user = userService.findUserByJwtToken(jwt);
            Cart cart = cartService.findUserById(user.getId());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }




}