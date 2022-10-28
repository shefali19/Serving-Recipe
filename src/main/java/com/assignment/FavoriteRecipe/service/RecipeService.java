package com.assignment.FavoriteRecipe.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.FavoriteRecipe.bean.Recipe;
import com.assignment.FavoriteRecipe.bean.RecipeSearchDTO;
import com.assignment.FavoriteRecipe.exception.RecipeIdNotFoundException;
import com.assignment.FavoriteRecipe.exception.RecipeNotFoundException;
import com.assignment.FavoriteRecipe.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepository;

	public RecipeService() {
	}

	public void insert(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	public Optional<Recipe> getRecipeById(Long id) {
		return recipeRepository.findById(id);
	}

	public Optional<Recipe> getRecipeByName(String recipeName) {
		Optional<Recipe> recipe = recipeRepository.findByRecipeName(recipeName);
		return recipe;
	}

	public List<Recipe> searchRecipe(RecipeSearchDTO recipeSearchDTO) {
		List<Recipe> recipesList = null;
		recipesList = filterRecipe(recipeSearchDTO);
		return recipesList;
	}

	private List<Recipe> filterRecipe(RecipeSearchDTO recipeSearchDTO) {

		List<Recipe> recipesList;
		Optional<Boolean> vegOrNonVeg = Optional.ofNullable(recipeSearchDTO.getIsVegetarian());
		Optional<Integer> servings = Optional.ofNullable(recipeSearchDTO.getServingSize());
		Optional<Boolean> isIngredientsIncluded = Optional.ofNullable(recipeSearchDTO.getIsIngredientsIncluded());
		Optional<String> cookingInstruction = Optional.ofNullable(recipeSearchDTO.getCookingInstructions());

		recipesList = recipeRepository.findAll().stream().filter(recipes -> {
			boolean recipe;

			if (vegOrNonVeg.isPresent()) {
				recipe = (recipes.getIsVegetarian().equals(recipeSearchDTO.getIsVegetarian()));
			}
			if (servings.isPresent()) {
				recipe = (recipes.getServingSize().equals(recipeSearchDTO.getServingSize()));
			}
			if (isIngredientsIncluded.isPresent()) {
				recipe = (recipes.getIngredients().contains(recipeSearchDTO.getIngredient()));
			} else {
				recipe = (!recipes.getIngredients().contains(recipeSearchDTO.getIngredient()));
			}
			if (cookingInstruction.isPresent()) {
				recipe = (recipes.getCookingInstructions().contains(recipeSearchDTO.getCookingInstructions()));
			}

			return recipe;
		}).collect(Collectors.toList());
		if (recipesList.isEmpty()) {
			throw new RecipeNotFoundException("Recipe for particular instructions and ingredients not found");
		}
		return recipesList;
	}

	public List<Recipe> getRecipeList() {
		List<Recipe> recipe = recipeRepository.findAll();
		return recipe;
	}

	public void deleteRecipeById(Long id) {
		recipeRepository.findById(id).orElseThrow(() -> {
			throw new RecipeIdNotFoundException("Recipe id in not found to delete", id);
		});
		recipeRepository.deleteById(id);
	}
}
