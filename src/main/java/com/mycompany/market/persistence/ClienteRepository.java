package com.mycompany.market.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.market.domain.Client;
import com.mycompany.market.domain.Product;
import com.mycompany.market.domain.repository.ClientRepository;
import com.mycompany.market.persistence.crud.IClienteCrudRepository;
import com.mycompany.market.persistence.entities.Cliente;
import com.mycompany.market.persistence.mapper.ClientMapper;

@Repository
public class ClienteRepository implements ClientRepository{

	@Autowired
	private IClienteCrudRepository clienteRepository;
	
	@Autowired
	private ClientMapper clienteMapper;

	@Override
	public List<Client> getAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		return clienteMapper.toClients(clientes);
	}

	@Override
	public Optional<Client> getClient(String clientId) {		
		Optional<Cliente> cliente = clienteRepository.findById(clientId);		
		return cliente.map(c -> clienteMapper.toClient(c));		
	}

	@Override
	public Client save(Client client) {
		//convertir a modelo
		Cliente cliente = clienteMapper.toCliente(client);
		
		//Guardar en la base de datos
		cliente = clienteRepository.save(cliente);
		
		//Convertir a respuesta
		return clienteMapper.toClient(cliente);
	}

	@Override
	public boolean existsById(String id) {
		return clienteRepository.existsById(id);
	}

	@Override
	public Client update(Client client) {
		//Encontrar cliente
		Cliente clienteEntity = clienteRepository.findById(client.getClientId()).orElseThrow(() -> new EntityNotFoundException());
		
		//verificamos el id y si existe proceder a actualizar
		if(clienteRepository.existsById(clienteEntity.getId())) {
			clienteEntity.setNombre(client.getName());
			clienteEntity.setApellidos(client.getSurname());
			clienteEntity.setCelular(client.getPhone());
			clienteEntity.setDireccion(client.getAddress());
			clienteEntity.setCorreoElectronico(client.getEmail());
			//Guardamos cliente
			clienteEntity = clienteRepository.save(clienteEntity);
		}
		//Convertimos cliente en respuesta
		return clienteMapper.toClient(clienteEntity);		
	}

	@Override
	public void delete(String clientId) {
		//Encontrar cliente
		Optional<Cliente> optCliente = clienteRepository.findById(clientId);
		Cliente cliente;
		
		//validar que exista
		if(optCliente.isPresent()) {
			cliente = optCliente.orElseThrow();
		}else {
			throw new EntityNotFoundException("No se encontro el cliente");
		}
		
		if(cliente != null && clienteRepository.existsById(clientId)) {
			if(cliente.getEstado()) {
				cliente.setEstado(!cliente.isActive());
			}else {
				cliente.setEstado(cliente.isActive());
			}
		}				
		clienteRepository.save(cliente);
	}

	
}
