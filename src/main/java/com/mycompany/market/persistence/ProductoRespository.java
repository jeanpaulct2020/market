package com.mycompany.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.market.domain.Product;
import com.mycompany.market.domain.repository.ProductRepository;
import com.mycompany.market.persistence.crud.IProductoCrudRepository;
import com.mycompany.market.persistence.entities.Producto;
import com.mycompany.market.persistence.mapper.ProductMapper;

@Repository
public class ProductoRespository implements ProductRepository{
	
	@Autowired
	private IProductoCrudRepository productoCrudRepository;
	
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public List<Product> getAll(){
		List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
		return mapper.toProducts(productos);
	}	
	
	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		
		return Optional.of(mapper.toProducts(productos));
	}
	

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
		return productos.map(prods -> mapper.toProducts(prods));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		Optional<Producto> producto = productoCrudRepository.findById(productId);
		return producto.map(p -> mapper.toProduct(p));
	}
	
	@Override
	public Product save(Product product) {
		Producto producto = mapper.toProducto(product);
		
		Producto productoSave = productoCrudRepository.save(producto);
		
		return mapper.toProduct(productoSave);
	}
	
	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}

	

}
