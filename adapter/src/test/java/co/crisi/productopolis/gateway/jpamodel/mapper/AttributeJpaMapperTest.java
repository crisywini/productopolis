package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.domain.objectmother.AttributeMother;
import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.mapper.model.AttributeJpaEntityMother;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class AttributeJpaMapperTest {

    private final AttributeJpaMapper mapper = Mappers.getMapper(AttributeJpaMapper.class);

    @Test
    void testToEntity() {

        var attribute = AttributeMother.random();

        var entity = mapper.map(attribute);

        assertThat(entity)
                .isNotNull()
                .extracting(AttributeJpaEntity::getId, AttributeJpaEntity::getName, AttributeJpaEntity::getDescription)
                .contains(attribute.getId(), attribute.getName(), attribute.getDescription());
    }

    @Test
    void testToAttribute() {
        var entity = AttributeJpaEntityMother.random();

        var attribute = mapper.map(entity);

        assertThat(attribute)
                .isNotNull()
                .extracting(IAttribute::getId, IAttribute::getName, IAttribute::getDescription)
                .contains(entity.getId(), entity.getName(), entity.getDescription());
    }

}