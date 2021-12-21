package com.mycompany.market.domain.exceptions;

public class PurchaseException extends Exception{

	private static final long serialVersionUID = 1L;

	public PurchaseException() {
		super();
	}
	
	public PurchaseException(String message) {
		super(message);
	}
}
