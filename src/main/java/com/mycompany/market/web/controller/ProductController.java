package com.mycompany.market.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.market.domain.Product;
import com.mycompany.market.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		//return productService.getAll();
		List<Product> productos = productService.getAll(); 
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int productId){
		return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getByCategory(@PathVariable("categoryId") int categoryId){
		//return productService.getByCategory(categoryId);
		return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Product product) {
		//return 
		Product producto = productService.save(product);  
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> delete(@PathVariable int productId) {
		//return productService.delete(productId);
		if(productService.delete(productId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
