package com.mycompany.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.mycompany.market.domain.Product;

//para el dominio
public interface ProductRepository {

	List<Product> getAll();
	
	Optional<Product> getProduct(int productId);
	
	Optional<List<Product>> getByCategory(int categoryId);
	
	Optional<List<Product>> getScarseProducts(int quantity);	
	
	Product save(Product product);
	
	void delete(int productId);
}
