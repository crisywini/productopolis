package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.objectmother.BrandMother;
import co.crisi.productopolis.gateway.jpamodel.BrandJpaEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BrandJpaMapperTest {

    private final BrandJpaMapper mapper = Mappers.getMapper(BrandJpaMapper.class);

    @Test
    void mapBrandToJpaModel() {

        var brand = BrandMother.random();

        var jpaModel = mapper.map(brand);

        assertThat(jpaModel)
                .isNotNull()
                .extracting(BrandJpaEntity::getName, BrandJpaEntity::getId, BrandJpaEntity::getDescription)
                .contains(brand.getName(), brand.getId(), brand.getDescription());

    }

}