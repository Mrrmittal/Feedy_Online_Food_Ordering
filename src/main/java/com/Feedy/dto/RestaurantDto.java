package com.Feedy.dto;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Embeddable
public class RestaurantDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 1000)
    private List<String> images = new ArrayList<>();

    @Column(length = 1000)
    private String description;

}
