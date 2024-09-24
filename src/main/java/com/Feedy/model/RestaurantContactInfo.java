package com.Feedy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
public class RestaurantContactInfo {

    private String email;
    private String instagramId;
    private String facebookId;
    private String mobile;
    private String twitter;

}

