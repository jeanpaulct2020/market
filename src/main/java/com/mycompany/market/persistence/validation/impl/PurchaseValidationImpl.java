package com.mycompany.market.persistence.validation.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mycompany.market.persistence.validation.IPurchaseValidation;

@Service
public class PurchaseValidationImpl implements IPurchaseValidation{

	private DateTimeFormatter dateFormatter;
	
	@Override
	public boolean isDate(String date) {
		dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");				
		
		try {
			LocalDate.parse(date, dateFormatter);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validateComments(String comments) {
		if(comments.length()<5) {
			return false;
		}
				
		if(StringUtils.isBlank(comments) && comments.length()<5) {
			return false;
		}		
		
		return true;
	}

}
