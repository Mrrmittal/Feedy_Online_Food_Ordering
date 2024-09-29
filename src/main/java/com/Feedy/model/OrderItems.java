package com.Feedy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "Order_Items")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Food food;

    private Long quantity;
    private Long totalPrice;

    @ElementCollection
    private List<Ingredients> ingredients;
}
