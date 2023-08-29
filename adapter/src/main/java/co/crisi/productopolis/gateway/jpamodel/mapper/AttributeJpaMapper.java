package co.crisi.productopolis.gateway.jpamodel.mapper;


import co.crisi.productopolis.domain.Attribute;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AttributeJpaMapper {

    AttributeJpaEntity map(IAttribute attribute);

    Attribute map(AttributeJpaEntity entity);

}
