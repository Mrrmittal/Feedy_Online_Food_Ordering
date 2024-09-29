/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service;


import com.Feedy.model.Order;
import com.Feedy.model.User;
import com.Feedy.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

        public Order createOrder(OrderRequest request, User user) throws Exception;
        public Order updateOrder(Long orderId, String orderStatus) throws Exception;
        public void cancelOrder(Long orderId) throws Exception;
        public List<Order> getUsersOrder(Long userId) throws Exception;
        public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception;
        public Order findOrderById(Long orderId) throws Exception;
}
