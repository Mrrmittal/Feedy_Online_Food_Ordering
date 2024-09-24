/**
 * Author: Jatin Mittal
 * Date: 24-09-2024
 */

package com.Feedy.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {

    private long id;
    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;
}
