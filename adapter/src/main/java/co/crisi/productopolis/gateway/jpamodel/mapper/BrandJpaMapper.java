package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.Brand;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.gateway.jpamodel.BrandJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BrandJpaMapper {

    @Mapping(target = "products", ignore = true)
    BrandJpaEntity map(IBrand brand);

    @Mapping(target = "products", ignore = true)
    Brand map(BrandJpaEntity entity);

}
