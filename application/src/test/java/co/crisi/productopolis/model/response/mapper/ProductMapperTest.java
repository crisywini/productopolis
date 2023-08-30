package co.crisi.productopolis.model.response.mapper;

import co.crisi.productopolis.domain.objectmother.ProductMother;
import co.crisi.productopolis.model.response.ProductResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void mapToResponse() {
        var product = ProductMother.randomWithAttributesAndCategories();

        var response = mapper.map(product);

        assertThat(response)
                .isNotNull()
                .extracting(ProductResponse::id, ProductResponse::name, ProductResponse::isActive)
                .contains(product.getId(), product.getName(), product.isActive());
    }

}