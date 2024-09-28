/**
 * Author: Jatin Mittal
 * Date: 27-09-2024
 */

package com.Feedy.service.serviceImpl;

import com.Feedy.dto.RestaurantDto;
import com.Feedy.model.Address;
import com.Feedy.model.Restaurant;
import com.Feedy.model.User;
import com.Feedy.repository.AddressRepo;
import com.Feedy.repository.RestaurantRepo;
import com.Feedy.repository.UserRepo;
import com.Feedy.request.RestaurantRequest;
import com.Feedy.service.RestaurantService;
import com.Feedy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Restaurant createRestaurant(RestaurantRequest request, User user) {

        Address address = addressRepo.save(request.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInfo(request.getContactInfo());
        restaurant.setDescription(request.getDescription());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setCreationDate(LocalDateTime.now());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOwner(user);

        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest updateRestaurant) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }

        if (restaurant.getDescription()!=null) restaurant.setDescription(updateRestaurant.getDescription());
        if (restaurant.getName()!=null) restaurant.setName(updateRestaurant.getName());


        return restaurantRepo.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepo.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    @Override
    public List<Restaurant> findRestaurant(String keyword) {
        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> optional = restaurantRepo.findById(id);

        if (optional.isEmpty()){
            throw new Exception("Restaurant not found "+id);
        }

        return optional.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepo.findByOwnerId(userId);
        if (restaurant==null){
            throw new Exception("Restaurant not found by userId "+userId);
        }

        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setId(restaurantId);

//        if(user.getFavorites().contains(restaurantDto)){
//            user.getFavorites().remove(restaurantDto);
//        }
//
//        else {
//            user.getFavorites().add(restaurantDto);
//        }


        boolean isFavourite = false;
        List<RestaurantDto> favourites = user.getFavorites();
        for(RestaurantDto favourite : favourites){
            if(Objects.equals(favourite.getId(), restaurantId)){
                isFavourite = true;
                break;
            }
        }

        //if restaurant is already favourite, then remove it
        if(isFavourite){
            favourites.removeIf(favourite -> Objects.equals(favourite.getId(), restaurantId));
        }else {
            favourites.add(restaurantDto);
        }

        userRepo.save(user);
        return restaurantDto;
    }

    @Override
    public Restaurant updateStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepo.save(restaurant);
    }
}
