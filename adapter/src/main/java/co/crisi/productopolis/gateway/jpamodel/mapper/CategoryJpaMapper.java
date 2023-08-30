package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.Category;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.gateway.jpamodel.CategoryJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryJpaMapper {

    @Mapping(target = "lastUpdate", source = "lastUpdated")
    CategoryJpaEntity map(ICategory category);

    @Mapping(target = "lastUpdated", source = "lastUpdate")
    Category map(CategoryJpaEntity entity);

}
