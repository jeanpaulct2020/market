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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Operation(summary = "Get all supermarket products") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "OK")
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		//return productService.getAll();
		List<Product> productos = productService.getAll(); 
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}
	
	@Operation(summary = "Search a product with an ID") // descripcion de lo hace este recurso de la api
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "404", description = "Product Not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@Parameter(description = "id of product to be searched", required = true, example = "7" ) 
							@PathVariable("id") int productId){
		
		return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@Operation(summary = "Search a product with a category ID") // descripcion de lo hace este recurso de la api
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "404", description = "Product Not found")
	})	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getByCategory(@Parameter(description = "id of product to be searched", required = true, example = "7")
								@PathVariable("categoryId") int categoryId){
				
		return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@Operation(summary = "Save a products") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "CREATED")
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Product product) {
		//return 
		Product producto = productService.save(product);  
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "Delete a product with an ID") // descripcion de lo hace este recurso de la api
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "404", description = "Product Not found")
	})	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> delete(@Parameter(description = "id of product to be searched", required = true, example = "7" ) @PathVariable int productId) {
		//return productService.delete(productId);
		if(productService.delete(productId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
