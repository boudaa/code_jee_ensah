package com.ensah.core.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
		return new ResponseEntity<>("Entity not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
		return new ResponseEntity<>("Constraint violation: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<String> handlePersistenceException(PersistenceException ex) {
		return new ResponseEntity<>("Persistence error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

	// Generic handler for any other exceptions
	
	@ExceptionHandler
	public ResponseEntity<ErrorContact> handleException(Exception ex) {

		logger.error("error : ", ex);

		ErrorContact err = new ErrorContact();
		err.setCodeError(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setMesssage("Erreur inconnue !");
		err.setDescription("Erreur inconnue, veuillez res....");

		return new ResponseEntity<ErrorContact>(err, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}