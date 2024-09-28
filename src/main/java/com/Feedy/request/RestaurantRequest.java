/**
 * Author: Jatin Mittal
 * Date: 26-09-2024
 */

package com.Feedy.request;

import com.Feedy.model.Address;
import com.Feedy.model.RestaurantContactInfo;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private RestaurantContactInfo contactInfo;
    private String openingHours;
    private LocalDateTime creationDate;
    private List<String> images;


}
