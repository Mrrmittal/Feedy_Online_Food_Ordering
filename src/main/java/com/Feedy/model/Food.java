package com.Feedy.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Food_Table")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long price;
    private String description;

    @ManyToOne
    private FoodCategory foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean isAvailable;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVeg;
    private boolean isSeasonable;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredients> ingredients = new ArrayList<>();
}
