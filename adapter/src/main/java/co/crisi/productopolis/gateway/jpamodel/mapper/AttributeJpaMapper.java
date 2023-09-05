package co.crisi.productopolis.gateway.jpamodel.mapper;


import co.crisi.productopolis.domain.Attribute;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AttributeJpaMapper {

    AttributeJpaEntity map(IAttribute attribute);

    @Mapping(target = "value", expression = "java(defaultValue())")
    Attribute map(AttributeJpaEntity entity);

    List<AttributeJpaEntity> mapToEntities(List<IAttribute> attributes);

    List<Attribute> mapToAttributes(List<AttributeJpaEntity> entities);

    default String defaultValue() {
        return "0.0";
    }

}
