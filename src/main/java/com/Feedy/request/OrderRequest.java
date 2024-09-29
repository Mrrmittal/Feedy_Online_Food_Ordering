/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.request;

import com.Feedy.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address DeliveryAddress;

}
