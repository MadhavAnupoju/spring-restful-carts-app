package com.spring.restful.carts.app.validator;

import java.util.Arrays;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CartValidator {

	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ApiInputError numberFormatHandler(NumberFormatException nfe) {
		FieldError fieldError = new FieldError("ID", nfe.getMessage());
		ApiInputError apiInputError = new ApiInputError("400-123", Arrays.asList(fieldError));
		return apiInputError;
	}

	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ApiInputError emptyResultSetHandler(EmptyResultDataAccessException erde) {
		FieldError fieldError = new FieldError("Id", erde.getLocalizedMessage());
		ApiInputError apiInputError = new ApiInputError("404-123", Arrays.asList(fieldError));
		return apiInputError;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ApiError handleAnyOtherException(Exception e) {
		ApiError apiError = new ApiError("500-123", Arrays.asList(e.getMessage()));
		return apiError;
	}
}
