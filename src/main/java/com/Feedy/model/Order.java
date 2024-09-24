package com.Feedy.model;


import com.Feedy.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private Long totalAmount;

    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address deliveryAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> items = new ArrayList<>();

    private String paymentMethod;

    private Long totalItem;
    private Long totalPrice;

}
