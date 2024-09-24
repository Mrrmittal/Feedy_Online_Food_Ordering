package com.Feedy.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Target;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ingredients_Table")
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    @ManyToOne
    private Restaurant restaurant;
    private boolean isAvailable = true;

}
