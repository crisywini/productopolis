package co.crisi.productopolis.model.response.register.mapper;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.model.response.register.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper  {


    @Mapping(target= "id", expression = "java(product.getId())")
    @Mapping(target= "name", expression = "java(product.getName())")
    @Mapping(target= "isActive", expression = "java(product.isActive())")
    ProductResponse map(IProduct product);

}
