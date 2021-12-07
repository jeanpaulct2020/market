package com.mycompany.market.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.market.domain.Purchase;
import com.mycompany.market.domain.service.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

	
	@Autowired
	private PurchaseService purchaseService;
	
	@Operation(summary = "Get all market purchases") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "OK")
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Purchase> purchases = purchaseService.getAll();
		LOGGER.info(purchases.toString());
		return new ResponseEntity<>(purchases, HttpStatus.OK);
	}
	
	@Operation(summary = "Search a purchase with a client ID") // descripcion de lo hace este recurso de la api
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "404", description = "Purchase Not found")
	})	
	@GetMapping("/client/{clientId}")
	public ResponseEntity<?> getByclient(@Parameter(description = "id of cliente to be searched", required = true, example = "4546221" ) @PathVariable String clientId){
		return  purchaseService.getByClient(clientId).map(pruchases -> new ResponseEntity<>(pruchases, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));		
	}
	
	@Operation(summary = "Save a purchase") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "CREATED")
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Purchase purchase){
		Purchase purchaseSave = purchaseService.save(purchase);
		
		return new ResponseEntity<>(purchaseSave, HttpStatus.CREATED);
	}
	
}
