/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.Enum.OrderStatus;
import com.Feedy.model.*;
import com.Feedy.repository.*;
import com.Feedy.request.OrderRequest;
import com.Feedy.service.CartService;
import com.Feedy.service.OrderService;
import com.Feedy.service.RestaurantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;


    @Transactional   // Ensures atomicity of the operation
    @Override
    public Order createOrder(OrderRequest request, User user) throws Exception {
        Address shipeddAddress = request.getDeliveryAddress();
        Address savedAddress = addressRepo.save(shipeddAddress);

        // Check if the user's addresses already contain the saved address (optional: implement equals and hashcode on Address)
        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepo.save(user);
        }

        // Retrieve the restaurant and check for null
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        if (restaurant == null) {
            throw new Exception("Restaurant not found");
        }

        // Initialize the order
        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus(OrderStatus.ORDER_PENDING);
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        // Retrieve the user's cart
        Cart cart = cartService.findUserById(user.getId());
        if (cart == null || cart.getItems().isEmpty()) {
            throw new Exception("Cart is empty or not found");
        }

        List<OrderItems> orderItems = new ArrayList<>();

        // Convert CartItems to OrderItems
        for (CartItem cartItem : cart.getItems()){
            OrderItems orderItem = new OrderItems();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            // Save each OrderItem
            OrderItems savedOrderItem = orderItemRepo.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        // Calculate total price
        Long totalPrice = cartService.calculateCartTotal(cart);

        // Set the order items and total price
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        // Save the created order
        Order savedOrder = orderRepo.save(createdOrder);

        // Add the order to the restaurant's orders (check for circular references)
        restaurant.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("ON_THE_WAY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("ORDER_PENDING")
                || orderStatus.equals("COMPLETED")){

            order.setOrderStatus(OrderStatus.valueOf(orderStatus));
            return orderRepo.save(order);
        }

        throw new Exception("Please Select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepo.findByRestaurantId(restaurantId);
        if (orderStatus!= null){
            orders=orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> orderOptional = orderRepo.findById(orderId);
        if (orderOptional.isEmpty()){
            throw new Exception("Order Not Found..");
        }
        return orderOptional.get();
    }
}
