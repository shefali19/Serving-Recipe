package com.assignment.FavoriteRecipe.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<ErrorMessage> RecipeNotFoundException(RecipeNotFoundException ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RecipeIdNotFoundException.class)
	public ResponseEntity<ErrorMessage> RecipeIdNotFoundException(RecipeIdNotFoundException ex) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), ex.getMessage());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}
}
