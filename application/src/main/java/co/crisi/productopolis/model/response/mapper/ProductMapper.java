package co.crisi.productopolis.model.response.mapper;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductMapper  {


    @Mapping(target= "id", expression = "java(product.getId())")
    @Mapping(target= "name", expression = "java(product.getName())")
    @Mapping(target= "isActive", expression = "java(product.isActive())")
    ProductResponse map(IProduct product);

    List<ProductResponse> map(List<IProduct> products);

}
