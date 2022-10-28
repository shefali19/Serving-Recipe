package com.assignment.FavoriteRecipe.bean;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class RecipeSearchDTO {

    private String cookingInstructions;
    private String ingredient;
    private Boolean isVegetarian;
    private Integer servingSize;
    private Boolean isIngredientsIncluded;

    public RecipeSearchDTO(String cookingInstructions, String ingredient,
            Boolean isVegetarian, Integer servingSize, Boolean isIngredientsIncluded) {
        super();
        this.cookingInstructions = cookingInstructions;
        this.isVegetarian = isVegetarian;
        this.servingSize = servingSize;
        this.ingredient = ingredient;
        this.cookingInstructions = cookingInstructions;
        this.isIngredientsIncluded = isIngredientsIncluded;
    }

}
