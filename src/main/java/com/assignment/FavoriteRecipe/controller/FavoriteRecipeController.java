package com.assignment.FavoriteRecipe.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.assignment.FavoriteRecipe.bean.Recipe;
import com.assignment.FavoriteRecipe.bean.RecipeSearchDTO;
import com.assignment.FavoriteRecipe.exception.RecipeIdNotFoundException;
import com.assignment.FavoriteRecipe.exception.RecipeNotFoundException;
import com.assignment.FavoriteRecipe.service.RecipeService;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class FavoriteRecipeController {

	@Autowired
	private RecipeService recipeService;

	/**
	 * @param recipe information need to save in DB
	 * @return Saved object into database
	 */
	@PostMapping("/createRecipe")
	@ApiOperation(value = "Register Recipes", notes = "Register recipe in same format as JSON")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful Recipe created ", response = List.class) })
	public ResponseEntity<Object> createRecipe(@RequestBody Recipe recipe) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		if (recipe.getId() == null) {
			recipe.setCreateDate(sdf.format(date));
		}
		recipe.setUpdatedDate(sdf.format(date));
		recipeService.insert(recipe);
		return new ResponseEntity<>(recipe, HttpStatus.CREATED);
	}

	/**
	 * @param id Recipes id which need to be find
	 * @return one record matching the Recipes ID
	 * @throws RecipeNotFoundException exception if id is not found
	 */
	@GetMapping("/getRecipeById")
	@ApiOperation(value = "Get Recipe By Id", notes = "Retrieve recipe in same format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful retrievieal of recipe "),
			@ApiResponse(code = 404, message = "Recipe not found") })
	public ResponseEntity<Object> getRecipeById(@RequestParam(value = "id") Long id) {
		Optional<Recipe> recipe = recipeService.getRecipeById(id);
		recipeIsEmpty(recipe.isEmpty(), "Recipe Not Found with Id: " + id);
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	/**
	 * @param recipeName Recipes name which need to be find
	 * @return one record matching the Recipes name
	 * @throws RecipeNotFoundException exception if name is not found
	 */
	@GetMapping("/getRecipeByName")
	@ApiOperation(value = "get Recipes by Name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful retrival of recipe by Name"),
			@ApiResponse(code = 404, message = "Recipe not found") })
	public ResponseEntity<Object> getRecipeByName(@RequestParam(value = "recipeName") String recipeName) {
		Optional<Recipe> recipe = recipeService.getRecipeByName(recipeName);
		recipeIsEmpty(recipe.isEmpty(), "Recipe Not Found with recipeName: " + recipeName);
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	/**
	 * @param recipeSearch for searching a recipe by instructions and ingredients or
	 *                     excluding them
	 * @return will return list of recipes contains information
	 */
	@ApiOperation(value = "search by number of instructions and ingredient")
	@PostMapping(path = "/searchRecipe")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful retrieval of recipes by search"),
			@ApiResponse(code = 404, message = "Recipe Not Found with relevant search item") })
	public ResponseEntity<List<Object>> searchRecipe(@RequestBody RecipeSearchDTO recipeSearchDTO) {
		{
			List<Object> recipe = Collections.singletonList(recipeService.searchRecipe(recipeSearchDTO));
			recipeIsEmpty(recipe.isEmpty(), "Recipe Not Found with relevant search item: ");
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		}
	}

	/**
	 * @return Will return list of all Recipes in database
	 */
	@ApiOperation(value = "get all Recipes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful retreival of recipes"),
			@ApiResponse(code = 404, message = "Recipe list is Empty") })
	@GetMapping("/getRecipeList")
	public ResponseEntity<Object> getRecipeList() {
		List<Recipe> recipe = recipeService.getRecipeList();
		recipeIsEmpty(recipe.isEmpty(), "Recipe List is Empty");
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}

	private void recipeIsEmpty(boolean recipe, String Recipe_List_is_Empty) {
		if (recipe) {
			throw new RecipeNotFoundException(Recipe_List_is_Empty);
		}
	}

	/**
	 * @param id Recipes id which need to be deleted and has a ROLE_USER
	 * @throws RecipeIdNotFoundException exception if id is not found
	 */
	@DeleteMapping("/deleteRecipeById")
	@ApiOperation(value = "Delete Recipe by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete all recipes"),
			@ApiResponse(code = 404, message = "Recipe with Id is not valid") })
	public ResponseEntity<Object> deleteRecipeById(@RequestParam(value = "id") Long id) {
		if (id == null) {
			throw new RecipeIdNotFoundException("Recipe Id is compulsory to delete the recipe", id);
		}
		Optional<Recipe> result = recipeService.getRecipeById(id);
		recipeIsEmpty(result.isEmpty(), "Recipe Not Found with Id:" + id);
		recipeService.deleteRecipeById(id);
		return new ResponseEntity<>("Deleted Id: " + id, HttpStatus.OK);
	}
}
