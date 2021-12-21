package com.mycompany.market.persistence.validation;

public interface IClientValidation {

	boolean isValidDate(String date);
	
	boolean validateDni(String dni);
	
	boolean validateName(String name);
	
	boolean validateSurname(String surname);
		
	boolean validateAddress(String address);
	
	boolean validateEmail(String email);
	
}
