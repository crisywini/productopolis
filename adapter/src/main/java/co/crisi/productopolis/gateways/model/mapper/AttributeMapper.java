package co.crisi.productopolis.gateways.model.mapper;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.gateways.model.AttributeJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AttributeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", expression = "java(attribute.getName())")
    @Mapping(target = "description", expression = "java(attribute.getDescription())")
    @Mapping(target = "value", expression = "java(attribute.getValue())")
    AttributeJpaEntity mapToJpaEntityCreation(IAttribute attribute);
    

}
