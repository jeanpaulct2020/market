package com.mycompany.market.domain.exceptions;

public class ProductException extends Exception{

	private static final long serialVersionUID = 1L;

	public ProductException() {
		super();
	}
	
	public ProductException(String message) {
		super(message);
	}
}
