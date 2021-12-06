package com.mycompany.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mycompany.market.domain.Category;
import com.mycompany.market.persistence.entities.Categoria;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	//Convirtiendo categorias a category
	@Mappings({
		@Mapping(source = "idCategoria", target = "categoryId"),
		@Mapping(source = "descripcion", target = "category"),
		@Mapping(source = "estado", target = "active")
	})
	Category toCategory(Categoria categoria);
	
	
	@InheritInverseConfiguration //notacion para que mapstruct haga la conversion de forma inversa a la anterior
	@Mapping(target = "productos", ignore = true)//ignorar el campo que no va a ir en la conversion
	Categoria toCategoria(Category catregory);
	
}
