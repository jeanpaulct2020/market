package com.mycompany.market.web.controller;

import java.util.List;

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

@RestController
@RequestMapping(name = "/pruchases")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Purchase> purchases = purchaseService.getAll();
		return new ResponseEntity<>(purchases, HttpStatus.OK);
	}
	
	@GetMapping("/client/{clientId}")
	public ResponseEntity<?> getByclient(@PathVariable String clientId){
		return  purchaseService.getByClient(clientId).map(pruchases -> new ResponseEntity<>(pruchases, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));		
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Purchase purchase){
		Purchase purchaseSave = purchaseService.save(purchase);
		
		return new ResponseEntity<>(purchaseSave, HttpStatus.CREATED);
	}
	
}
