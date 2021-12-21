package com.mycompany.market.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.market.domain.Client;
import com.mycompany.market.domain.dto.Mensaje;
import com.mycompany.market.domain.exceptions.ClientException;
import com.mycompany.market.domain.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	
	@PreAuthorize("hasRole('VENTAS')")
	@Operation(summary = "Get all supermarket clients") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "OK")
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){		
		List<Client> clientes;
		try {
			clientes = clientService.getAll();
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (ClientException ce) {
			LOGGER.error("Error: ".concat(ce.getMessage()));
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.PRECONDITION_FAILED);
		} 
	}
	
	@PreAuthorize("hasRole('VENTAS')")
	@Operation(summary = "Search a client with an ID") // descripcion de lo hace este recurso de la api
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "404", description = "client Not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getClient(@Parameter(description = "id of client to be searched", required = true, example = "72162519" ) 
							@PathVariable("id") String clientId){
		
		try {
			return clientService.getClient(clientId).map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
					.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} catch (ClientException ce) {
			return new ResponseEntity<>(ce.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Save a client") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "CREATED")
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Client client) {
		//return 
		Client cliente;
		try {
			cliente = clientService.save(client);
			return new ResponseEntity<>(cliente, HttpStatus.CREATED);
		} catch (ClientException ce) {
			LOGGER.error("Error: ".concat(ce.getMessage()));
			return new ResponseEntity<>(ce.getMessage() , HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Error: ".concat(e.getMessage()));
			return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
		}  
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Active client") // descripcion de lo hace este recurso de la api
	@ApiResponse(responseCode = "200", description = "CREATED")
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Client client) {
		//return 
		Client cliente;
		try {
			cliente = clientService.update(client);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch (ClientException ce) {
			LOGGER.error("Error: ".concat(ce.getMessage()));
			return new ResponseEntity<>(ce.getMessage() , HttpStatus.PRECONDITION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Error: ".concat(e.getMessage()));
			return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
		}  		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Delete a client with an ID") // descripcion de lo hace este recurso de la api
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "404", description = "client Not found")
	})	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@Parameter(description = "id of client to be searched", required = true, example = "72015228" ) @PathVariable("id") String clientId) {
		//return productService.delete(productId);		
		try {
			clientService.delete(clientId);
			return new ResponseEntity<>(new Mensaje("Eliminado Correctamente!") , HttpStatus.OK);	
		}catch (ClientException ce) {
			LOGGER.error("Error:" + ce.getMessage());
			return new ResponseEntity<>(ce.getMessage() ,HttpStatus.PRECONDITION_FAILED);			
		}catch (Exception e) {
			LOGGER.error("Error:" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
	}
}
