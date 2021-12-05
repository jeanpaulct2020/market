package com.mycompany.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mycompany.market.persistence.crud.IProductoCrudRepository;
import com.mycompany.market.persistence.entities.Producto;

@Repository
public class ProductoRespository {
	
	private IProductoCrudRepository productoCrudRepository;
	
	List<Producto> getAll(){
		return (List<Producto>) productoCrudRepository.findAll();
	}
	
	public List<Producto> getByCategoria(Integer idCategoria){
		return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
	}
	
	public Optional<List<Producto>> getProductosEscasos(int cantidad){
		return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
	}

	Optional<Producto> getProducto(Integer idProducto){
		return productoCrudRepository.findById(idProducto);
	}
	
	Producto save(Producto producto) {
		return productoCrudRepository.save(producto);
	}
	
	public void delete(int idProducto) {
		productoCrudRepository.deleteById(idProducto);
	}
}
