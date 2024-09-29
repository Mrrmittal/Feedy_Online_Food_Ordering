/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.model.Cart;
import com.Feedy.model.CartItem;
import com.Feedy.model.Food;
import com.Feedy.model.User;
import com.Feedy.repository.CartItemRepo;
import com.Feedy.repository.CartRepo;
import com.Feedy.request.AddCartItemRequest;
import com.Feedy.service.CartService;
import com.Feedy.service.FoodService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private FoodService foodService;


    @Override
    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(request.getFoodId());

        Cart cart = cartRepo.findByUserId(user.getId());

        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getFood().equals(food)){
                Long newQuantity = cartItem.getQuantity() + request.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItem newCartItem= new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(request.getQuantity());
        newCartItem.setIngredients(request.getIngredients());
        newCartItem.setTotalPrice(request.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepo.save(newCartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Long quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found...");
        }

        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);

        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepo.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepo.findByUserId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart item not found...");
        }

        CartItem cartItem = cartItemOptional.get();
        cart.getItems().remove(cartItem);

        return cartRepo.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {
        Long total = 0L;

        for(CartItem cartItem : cart.getItems()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cartOptional = cartRepo.findById(id);
        if (cartOptional.isEmpty()){
            throw new Exception("Cart item not found...");
        }
        return cartOptional.get();
    }

    @Override
    public Cart findUserCart(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return cartRepo.findByUserId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        Cart cart = findUserCart(jwt);
        cart.getItems().clear();

        return cartRepo.save(cart);
    }
}
