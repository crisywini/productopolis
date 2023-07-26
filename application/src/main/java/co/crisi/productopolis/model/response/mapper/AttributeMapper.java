package co.crisi.productopolis.model.response.mapper;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AttributeMapper {

    @Mapping(target = "id", expression = "java(attribute.getId())")
    @Mapping(target = "name", expression = "java(attribute.getName())")
    @Mapping(target = "description", expression = "java(attribute.getDescription())")
    @Mapping(target = "value", expression = "java(attribute.getValue())")
    AttributeResponse map(IAttribute attribute);

    @Mapping(target = "id", expression = "java(attribute.getId())")
    @Mapping(target = "name", expression = "java(attribute.getName())")
    @Mapping(target = "description", expression = "java(attribute.getDescription())")
    @Mapping(target = "value", expression = "java(attribute.getValue())")
    AttributeRequest mapToRequest(IAttribute attribute);

}
