package co.crisi.productopolis.model.response.extract.mapper;

import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.model.response.extract.BrandResponse;
import org.mapstruct.Mapping;

public interface BrandMapper {

    @Mapping(target = "id", expression = "java(response.getId())")
    @Mapping(target = "name", expression = "java(response.getId())")
    IBrand map(BrandResponse response);

}
