package com.mycompany.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.market.domain.Product;
import com.mycompany.market.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAll(){
		return productRepository.getAll();
	}
	 
	public Optional<Product> getProduct(int productId){
		return productRepository.getProduct(productId);
	}
	
	public Optional<List<Product>> getByCategory(int categoryId){
		return productRepository.getByCategory(categoryId);
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public boolean delete(int productId) {
		/*
		return getProduct(producId).map(product -> {
			productRepository.delete(producId);
			return true;
		}).orElse(false);*/
		
		if(getProduct(productId).isPresent()) {
			productRepository.delete(productId);
			return true;
		} else {
			return false;
		}
		
	}
	

}
