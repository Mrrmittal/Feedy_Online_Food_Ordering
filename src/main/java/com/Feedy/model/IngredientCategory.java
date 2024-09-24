/**
 * Author: Jatin Mittal
 * Date: 25-09-2024
 */

package com.Feedy.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "ingredientCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredients> ingredients = new ArrayList<>();


}
