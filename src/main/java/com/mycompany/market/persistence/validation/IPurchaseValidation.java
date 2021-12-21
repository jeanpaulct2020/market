package com.mycompany.market.persistence.validation;

public interface IPurchaseValidation {

	boolean isDate(String date);
	boolean validateComments(String comments);
	
}
