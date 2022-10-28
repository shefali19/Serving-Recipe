package com.assignment.FavoriteRecipe.exception;

public class RecipeIdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecipeIdNotFoundException(String msg, Long id) {
		super(msg);
	}
}
