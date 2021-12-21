package com.mycompany.market.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mycompany.market.domain.Client;
import com.mycompany.market.persistence.entities.Cliente;

@Mapper(componentModel = "spring", uses = {PurchaseMapper.class})
public interface ClientMapper {

	@Mappings({
		@Mapping(source = "id", target = "clientId" ),
		@Mapping(source = "nombre", target = "name" ),
		@Mapping(source = "apellidos", target = "surname" ),
		@Mapping(source = "celular", target = "phone" ),
		@Mapping(source = "direccion", target = "address" ),
		@Mapping(source = "correoElectronico", target = "email" ),
		@Mapping(source = "estado", target = "active" )
		//@Mapping(source = "compras", target = "purchases")
	})
	Client toClient(Cliente cliente);
	List<Client> toClients(List<Cliente> clientes);
	
	@InheritInverseConfiguration
	@Mappings({
		@Mapping(target = "compras", ignore = true)
	})
	Cliente toCliente(Client client);
}
