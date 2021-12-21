package com.mycompany.market.domain.exceptions;

public class ClientException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientException() {
		super();
	}
	
	public ClientException(String message) {
		super(message);
	}
	
}
