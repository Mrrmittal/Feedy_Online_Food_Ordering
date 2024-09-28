/**
 * Author: Jatin Mittal
 * Date: 28-09-2024
 */

package com.Feedy.request;

import com.Feedy.model.FoodCategory;
import com.Feedy.model.IngredientCategory;
import com.Feedy.model.Ingredients;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private Long price;
    private String depreciation;
    private FoodCategory foodCategory;
    private List<String> image;
    private Long restaurantId;
    private boolean isVeg;
    private boolean isSeasonable;
    private List<Ingredients> ingredients;
}
