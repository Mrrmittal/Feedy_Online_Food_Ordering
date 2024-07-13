package com.Feedy.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "User_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

}
