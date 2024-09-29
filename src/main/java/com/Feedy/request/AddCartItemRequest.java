/**
 * Author: Jatin Mittal
 * Date: 29-09-2024
 */

package com.Feedy.request;

import com.Feedy.model.Ingredients;
import lombok.Data;

import java.util.List;


@Data
public class AddCartItemRequest {
    private Long foodId;
    private Long quantity;
    private List<Ingredients> ingredients;

}
