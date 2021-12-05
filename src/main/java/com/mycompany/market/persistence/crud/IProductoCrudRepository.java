package com.mycompany.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.market.persistence.entities.Producto;

public interface IProductoCrudRepository extends CrudRepository<Producto, Integer>{

	//@Query(value = "SELECT * FROM productos WHERE id_categoria=?", nativeQuery = true)
	//List<Producto> getByIdCategoria(Integer idCategoria);
	
	List<Producto> findByIdCategoriaOrderByNombreAsc(Integer idCategoria);
	
	Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);
}
