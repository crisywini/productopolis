package co.crisi.productopolis.model.response.mapper;

import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "id", expression = "java(category.getId())")
    @Mapping(target = "name", expression = "java(category.getName())")
    @Mapping(target = "description", expression = "java(category.getDescription())")
    @Mapping(target = "creationDate", expression = "java(category.getCreationDate())")
    @Mapping(target = "lastUpdated", expression = "java(category.getLastUpdated())")
    CategoryResponse map(ICategory category);

}
