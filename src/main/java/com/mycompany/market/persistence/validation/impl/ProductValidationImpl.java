package com.mycompany.market.persistence.validation.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mycompany.market.persistence.validation.IProductValidation;

@Service
public class ProductValidationImpl implements IProductValidation{

	@Override
	public boolean validateName(String name) {
		if(name.length()<3) {
			return false;
		}
				
		if(StringUtils.isBlank(name) && name.length()<3) {
			return false;
		}		
		
		return true;
	}

	@Override
	public boolean validateStock(int stock) {
		if(stock < 0) {
			return false;
		}
		return true;
	}

}
