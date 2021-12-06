package com.mycompany.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.market.persistence.entities.Compra;

public interface ICompraCrudRepository extends CrudRepository<Compra, Integer>{

	Optional<List<Compra>> findByIdCliente(String idCliente);
}
