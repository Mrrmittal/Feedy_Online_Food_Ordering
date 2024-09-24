package com.Feedy.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "User_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String flatNo;
    private String street;
    private String city;
    private String state;
    private String pinCode;

}
