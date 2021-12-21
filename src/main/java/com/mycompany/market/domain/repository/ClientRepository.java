package com.mycompany.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.mycompany.market.domain.Client;
import com.mycompany.market.domain.Product;

public interface ClientRepository {

	List<Client> getAll();
	
	Optional<Client> getClient(String clientId);
	
	//Optional<List<Client>> getByName(int categoryId);
	
	//Optional<List<Product>> getScarseProducts(int quantity);	
	
	boolean existsById(String id);
	
	Client save(Client client);
	
	Client update(Client client);
	
	void delete(String clientId);
}
