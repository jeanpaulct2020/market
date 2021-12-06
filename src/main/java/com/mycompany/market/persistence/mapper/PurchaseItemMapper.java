package com.mycompany.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mycompany.market.domain.PurchaseItem;
import com.mycompany.market.persistence.entities.ComprasProducto;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

	@Mappings({
		@Mapping(source = "id.idProducto", target = "productId"),
		@Mapping(source = "cantidad", target = "quantity"),
		@Mapping(source = "total", target = "total"), //se puede suprimir
		@Mapping(source = "estado", target = "active")
	})
	PurchaseItem toPurchaseItem(ComprasProducto producto);
	
	@InheritInverseConfiguration
	@Mappings({
		@Mapping(target = "compra", ignore = true),
		@Mapping(target = "producto", ignore = true),
		@Mapping(target = "id.idCompra", ignore = true)
		
	})
	ComprasProducto toComprasProducto(PurchaseItem item);
}
