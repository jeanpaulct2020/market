package com.mycompany.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.market.domain.Purchase;
import com.mycompany.market.domain.repository.PurchaseRepository;
import com.mycompany.market.persistence.crud.ICompraCrudRepository;
import com.mycompany.market.persistence.entities.Compra;
import com.mycompany.market.persistence.mapper.ProductMapper;
import com.mycompany.market.persistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository{

	@Autowired
	private ICompraCrudRepository compraRepository;
	
	@Autowired
	private PurchaseMapper mapper;
	
	@Override
	public List<Purchase> getAll() {
		List<Compra> compras = (List<Compra>) compraRepository.findAll();
		
		return mapper.toPurchases(compras);
	}

	@Override
	public Optional<List<Purchase>> getByCliente(String clientId) {
		
		Optional<List<Purchase>> compras = compraRepository.findByIdCliente(clientId)
				.map(compra -> mapper.toPurchases(compra));		
		return compras;
	}

	@Override
	public Purchase save(Purchase purchase) {
		
		//convetir a compra - entity
		Compra compra = mapper.toCompra(purchase);
		
		//asignar cada producto a la compra
		compra.getProductos().forEach(producto -> producto.setCompra(compra));
		
		//guardarlo en la bd
		Compra compraSave = compraRepository.save(compra);
		
		//convetir a dominio
		return mapper.toPurchase(compraSave);
	}

}
