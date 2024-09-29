/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service;

import com.Feedy.model.Cart;
import com.Feedy.model.CartItem;
import com.Feedy.request.AddCartItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest request, String jwt) throws Exception;
    public CartItem updateCartItemQuantity(Long cartItemId, Long quantity) throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
    public Long calculateCartTotal(Cart cart) throws Exception;
    public Cart findCartById(Long id) throws Exception;
    public Cart findCartByUserId(Long userId) throws Exception;
    public Cart clearCart(Long userId) throws Exception;
}
