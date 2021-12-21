package com.mycompany.market.persistence.validation.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mycompany.market.persistence.validation.IClientValidation;

@Service 
public class ClientValidationImpl implements IClientValidation{
	
	private DateTimeFormatter dateFormatter;
	
	@Override
	public boolean validateDni(String dni) {
		if(dni.length()!=8) {
			return false;
		}		
		
		if(dni.length()>8) {
			return false;
		}
		
		if(StringUtils.isBlank(dni) && dni.length()!=9) {
			return false;
		}
		
		if( dni.matches("^[0-9]*$")) {
			return true;
		}
		
		return true;
	}
	
	@Override
	public boolean isValidDate(String date) {
		dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");				
		
		try {
			LocalDate.parse(date, dateFormatter);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

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
	public boolean validateSurname(String surname) {
		if(surname.length()<3) {
			return false;
		}
				
		if(StringUtils.isBlank(surname) && surname.length()<3) {
			return false;
		}		
		
		return true;
	}

	@Override
	public boolean validateAddress(String address) {
				
		if(StringUtils.isBlank(address)) {
			return false;
		}		
		
		return true;
	}

	@Override
	public boolean validateEmail(String email) {
		if(StringUtils.isBlank(email)) {
			return false;
		}
		return true;
	}

	

}
