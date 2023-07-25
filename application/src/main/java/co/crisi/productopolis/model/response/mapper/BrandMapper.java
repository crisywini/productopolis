package co.crisi.productopolis.model.response.mapper;

import co.crisi.productopolis.domain.Brand;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.model.request.BrandRequest;
import co.crisi.productopolis.model.response.BrandResponse;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = ArrayList.class)
public interface BrandMapper {

    @Mapping(target = "id", expression = "java(brand.getId())")
    @Mapping(target = "name", expression = "java(brand.getName())")
    @Mapping(target = "description", expression = "java(brand.getDescription())")
    BrandResponse map(IBrand brand);

    @Mapping(target = "id", expression = "java(request.id())")
    @Mapping(target = "name", expression = "java(request.name())")
    @Mapping(target = "description", expression = "java(request.description())")
    @Mapping(target = "products", expression = "java(new ArrayList<>())")
    Brand map(BrandRequest request);


    List<BrandResponse> map(List<IBrand> brands);

}
