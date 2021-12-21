package com.mycompany.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.market.domain.Purchase;
import com.mycompany.market.domain.exceptions.ClientException;
import com.mycompany.market.domain.exceptions.ProductException;
import com.mycompany.market.domain.exceptions.PurchaseException;
import com.mycompany.market.domain.repository.PurchaseRepository;
import com.mycompany.market.persistence.validation.IClientValidation;
import com.mycompany.market.persistence.validation.IPurchaseValidation;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private IPurchaseValidation purchaseValidation;
	
	@Autowired
	private IClientValidation clientValidation;
	
	public List<Purchase> getAll() throws PurchaseException{
		
		List<Purchase> purchases = purchaseRepository.getAll(); 
		
		if(purchases.isEmpty()) {
			throw new PurchaseException("La lista de compras esta vacia");
		}
		
		return purchases;
	}
	
	public Optional<List<Purchase>> getByClient(String clientId) throws ClientException{
		
		if(!clientValidation.validateDni(clientId) ) {
			throw new ClientException("El DNI ingresado no debe estar vacio y/o debe tener 8 caracteres");
		}		
		return purchaseRepository.getByCliente(clientId);
	}
	
	public Purchase save(Purchase purchase) throws PurchaseException {
		
		if(purchaseValidation.isDate(purchase.getDate().toString())) {
			throw new PurchaseException("La fecha no cumple con el formato establecido");
		}
		
		if(purchaseValidation.validateComments(purchase.getComents() )) {
			throw new PurchaseException("El comentario no debe estar vacio");
		}
		
		return purchaseRepository.save(purchase);
	}
}
