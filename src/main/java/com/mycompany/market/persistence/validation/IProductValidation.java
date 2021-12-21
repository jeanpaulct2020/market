package com.mycompany.market.persistence.validation;

public interface IProductValidation {

	boolean validateName(String name);
		
	boolean validateStock(int stock);
}
