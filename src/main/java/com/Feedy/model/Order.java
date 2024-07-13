package com.Feedy.model;


import com.Feedy.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Order_details")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    List<OrderItems> items = new ArrayList<>();

    private String paymentMethod;

    private Long totalItem;



}
