package com.assignment.FavoriteRecipe.exception;

public class RecipeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecipeNotFoundException(String msg) {
		super(msg);
	}
}
