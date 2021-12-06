package com.mycompany.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.mycompany.market.domain.Purchase;

public interface PurchaseRepository {

	//Lista de todas las compras realizadas
	List<Purchase> getAll();
	
	//lista de compras realizadas por un cliente en particular
	Optional<List<Purchase>> getByCliente(String clientId);
	
	//Guardar una compra	
	Purchase save(Purchase purchase);
	
}
