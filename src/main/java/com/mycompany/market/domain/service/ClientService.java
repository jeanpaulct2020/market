package com.mycompany.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.market.domain.Client;
import com.mycompany.market.domain.exceptions.ClientException;
import com.mycompany.market.domain.repository.ClientRepository;
import com.mycompany.market.persistence.validation.IClientValidation;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private IClientValidation clientValidation;
	
	public List<Client> getAll() throws ClientException{
		List<Client> clientes = clientRepository.getAll();
		if(clientes.isEmpty()) {
			throw new ClientException("La lista esta vacia");
		}
		
		return clientRepository.getAll();
	}
	
	public Optional<Client> getClient(String clientId) throws ClientException{
		 Optional<Client> clientOpt = clientRepository.getClient(clientId);
		 
		 Client client;
		 
		 if(clientOpt.isPresent()) {
			 client = clientOpt.orElseThrow();
		 } else {
			 throw new ClientException("No se encuentró ".concat("El cliente con dni ").concat(clientId));
		 }
		
		 return Optional.of(client);
	}
	
	public boolean existsById(String id) {
		return clientRepository.existsById(id);
	}
	
	public Client save(Client client) throws ClientException {
		
		if(existsById(client.getClientId())){
			throw new ClientException("El DNI ya se encuentra registrado");
		}
		
		if(!clientValidation.validateDni(client.getClientId()) ) {
			throw new ClientException("El DNI ingresado no debe estar vacio y/o debe tener 8 caracteres");
		}
		
		if(!clientValidation.validateName(client.getName()) ) {
			throw new ClientException("El nombre ingresado no debe estar vacio y/o debe tener mas de 2 caracteres");
		}
		
		if(!clientValidation.validateSurname(client.getSurname()) ) {
			throw new ClientException("El apellido ingresado no debe estar vacio y/o debe tener mas de 2 caracteres");
		}
		
		if(!clientValidation.validateAddress(client.getAddress()) ) {
			throw new ClientException("La direccion ingresada no debe estar vacia");
		}
		
		if(!clientValidation.validateEmail(client.getEmail()) ) {
			throw new ClientException("El correo ingresado no debe estar vacio");
		}					
		
		return clientRepository.save(client);
	}
	
	public Client update(Client client) throws ClientException {
		
		if(!clientValidation.validateDni(client.getClientId()) ) {
			throw new ClientException("El DNI ingresado no debe estar vacio y/o debe tener 8 caracteres");
		}
		
		
		if(!clientValidation.validateName(client.getName()) ) {
			throw new ClientException("El nombre ingresado no debe estar vacio y/o debe tener mas de 2 caracteres");
		}
		
		if(!clientValidation.validateSurname(client.getSurname()) ) {
			throw new ClientException("El apellido ingresado no debe estar vacio y/o debe tener mas de 2 caracteres");
		}
		
		if(!clientValidation.validateAddress(client.getAddress()) ) {
			throw new ClientException("La direccion ingresada no debe estar vacia");
		}
		
		if(!clientValidation.validateEmail(client.getEmail()) ) {
			throw new ClientException("El correo ingresado no debe estar vacio");
		}		
		
		return clientRepository.update(client);
	}
	
	public void delete(String clientId) throws ClientException {		
		if(clientId != null) {
			clientRepository.delete(clientId);
		} else {
			throw new ClientException("El cliente no fue encontrado");
		}				
	}
}
