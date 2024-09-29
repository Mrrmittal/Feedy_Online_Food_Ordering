/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.request;

import lombok.Data;

@Data
public class IngredientItemsRequest {
    private String name;
    private Long categoryId;
    private Long restaurantId;
}
