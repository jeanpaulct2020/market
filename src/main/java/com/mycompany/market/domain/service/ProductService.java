package com.mycompany.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.market.domain.Product;
import com.mycompany.market.domain.exceptions.ProductException;
import com.mycompany.market.domain.repository.ProductRepository;
import com.mycompany.market.persistence.validation.IProductValidation;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private IProductValidation productValidation;
	
	public List<Product> getAll() throws ProductException{
		
		List<Product> productos = productRepository.getAll();
		
		if(productos.isEmpty()) {
			throw new ProductException("La lista de productos esta vacia");
		}
		
		return productRepository.getAll();
	}
	 
	public Optional<Product> getProduct(int productId) throws ProductException{
		
		Optional<Product> productOpt = productRepository.getProduct(productId);
		String id = String.valueOf(productId);
		
		Product product;
		
		if(productOpt.isPresent()) {
			product = productOpt.orElseThrow();
		} else {
			throw new ProductException("No se encuentró ".concat("El producto con el ID ").concat(id));
		}
		return Optional.of(product);
	}
	
	public Optional<List<Product>> getByCategory(int categoryId){
		return productRepository.getByCategory(categoryId);
	}
	
	public Product save(Product product) throws ProductException {
		
		if(productValidation.validateName(product.getName())) {
			throw new ProductException("El nombre ingresado no debe estar vacio y/o debe tener mas de 2 caracteres");
		}
		
		if(productValidation.validateStock(product.getStock())) {
			throw new ProductException("El stock no debe tener una cantidad menor a 0");
		}
		
		return productRepository.save(product);
	}
	
	public boolean delete(int productId) throws ProductException {
		
		if(getProduct(productId).isPresent()) {
			productRepository.delete(productId);
			return true;
		} else {
			return false;			
		}
	}
	

}
