package com.example.FavoriteRecipe.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.assignment.FavoriteRecipe.FavoriteRecipeApplication;
import com.assignment.FavoriteRecipe.bean.Recipe;
import com.assignment.FavoriteRecipe.bean.RecipeSearchDTO;
import com.assignment.FavoriteRecipe.controller.FavoriteRecipeController;
import com.assignment.FavoriteRecipe.service.RecipeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FavoriteRecipeApplication.class)
class FavoriteRecipeControllerTest {

	@Autowired
	private FavoriteRecipeController theFavoriteRecipeController;

	@MockBean
	private RecipeService mockCustomerDaoService;

	@Test
	public void givenRecipeObjectthenCreateRecipe() {
		Recipe mockRecipe = getRecipeObject().get();
		theFavoriteRecipeController.createRecipe(mockRecipe);
		verify(mockCustomerDaoService, times(1)).insert(mockRecipe);
	}

	@Test
	public void givenRecipeSearch() {
		RecipeSearchDTO mockRecipe = getRecipeSearchObject().get();
		theFavoriteRecipeController.searchRecipe(mockRecipe);
		verify(mockCustomerDaoService, times(1)).searchRecipe(mockRecipe);

	}

	@Test
	public void givenRecipeIdthenGetRecipeById() {
		Long expectedId = (long) 1;
		when(mockCustomerDaoService.getRecipeById(expectedId)).thenReturn(getRecipeObject());
		theFavoriteRecipeController.getRecipeById(expectedId);
		verify(mockCustomerDaoService, times(1)).getRecipeById(expectedId);
	}

	@Test
	public void givenRecipeNamethenGetRecipeByName() {
		String expectedRecipeName = "Kabab";
		when(mockCustomerDaoService.getRecipeByName(expectedRecipeName)).thenReturn(getRecipeObject());
		theFavoriteRecipeController.getRecipeByName(expectedRecipeName);
		verify(mockCustomerDaoService, times(1)).getRecipeByName(expectedRecipeName);
	}

	@Test
	public void givenRecipethenGetRecipeList() {
		when(mockCustomerDaoService.getRecipeList()).thenReturn(List.of(getRecipeObject().get()));
		theFavoriteRecipeController.getRecipeList();
		verify(mockCustomerDaoService, times(1)).getRecipeList();
	}

	@Test
	public void givenRecipeIdthenDeleteRecipeById() {
		Long expectedId = 1l;
		when(mockCustomerDaoService.getRecipeById(expectedId)).thenReturn(getRecipeObject());
		theFavoriteRecipeController.deleteRecipeById(expectedId);
		verify(mockCustomerDaoService, times(1)).getRecipeById(expectedId);
		verify(mockCustomerDaoService, times(1)).deleteRecipeById(expectedId);
	}

	public Optional<Recipe> getRecipeObject() {
		List<String> ingredients = new ArrayList<String>();
		List<String> cookingInstructions = new ArrayList<String>();
		ingredients.add("oil");
		ingredients.add("cumin");
		cookingInstructions.add("heat");
		cookingInstructions.add("boil");
		return Optional.of(new Recipe("Kabab", true, 4, ingredients, cookingInstructions));
	}

	public Optional<RecipeSearchDTO> getRecipeSearchObject() {
		return Optional.of(new RecipeSearchDTO("heat", "kabab", true, 4, true));
	}

}
