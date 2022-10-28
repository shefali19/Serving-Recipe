package com.assignment.FavoriteRecipe.bean;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Recipe {
	@Id
	@GeneratedValue
	private Long id;
	private String recipeName;
	private String createDate;
	private Boolean isVegetarian;
	private Integer servingSize;
	@ElementCollection
	private List<String> ingredients;
	@ElementCollection
	private List<String> cookingInstructions;
	@JsonIgnore
	private String updatedDate;

	public Recipe() {
	}

	public Recipe(String recipeName, Boolean isVegetarian, Integer servingSize,
			List<String> ingredients, List<String> cookingInstructions) {
		super();
		this.recipeName = recipeName;
		this.isVegetarian = isVegetarian;
		this.servingSize = servingSize;
		this.ingredients = ingredients;
		this.cookingInstructions = cookingInstructions;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", recipeName=" + recipeName + ", createDate=" + createDate + ", isVegetarian="
				+ isVegetarian + ", servingSize=" + servingSize + ", ingredients=" + ingredients
				+ ", cookingInstructions=" + cookingInstructions + ", updatedDate=" + updatedDate + "]";
	}

}
