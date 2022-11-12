package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ContatoNotFoundAdvice {
	
	@ResponseBody
	  @ExceptionHandler(ContatoNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String ContatoNotFoundHandler(ContatoNotFoundException ex) {
	    return ex.getMessage();
	  }

}
