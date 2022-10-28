package com.assignment.FavoriteRecipe.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.FavoriteRecipe.bean.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	public Optional<Recipe> findByRecipeName(String recipeName);

}
