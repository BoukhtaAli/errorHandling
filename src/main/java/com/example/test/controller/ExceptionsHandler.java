package com.example.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

	private static final String TECHNICAL = "TECHNICAL EXCEPTION";

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		log.error(TECHNICAL, illegalArgumentException);
		return new ResponseEntity<>(TECHNICAL, HttpStatus.BAD_REQUEST);
	}
}
