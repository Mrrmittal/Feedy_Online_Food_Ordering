/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.service;

import com.Feedy.dto.RestaurantDto;
import com.Feedy.model.Restaurant;
import com.Feedy.model.User;
import com.Feedy.request.RestaurantRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RestaurantService {

    public Restaurant createRestaurant(RestaurantRequest request, User user);
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest updateRestaurant ) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> findRestaurant(String keyword);
    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
    public Restaurant updateStatus(Long id) throws Exception;
}
