package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.gateway.jpamodel.ProductJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductJpaMapper {

    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    ProductJpaEntity map(IProduct product);


}
