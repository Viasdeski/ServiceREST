package com.example.demo;

public class ContatoNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ContatoNotFoundException(Long id) {
	    super("Não foi posspivel encontrar o contato: " + id);
	  }

}
