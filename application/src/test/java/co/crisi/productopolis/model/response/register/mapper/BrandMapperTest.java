package co.crisi.productopolis.model.response.register.mapper;

import co.crisi.productopolis.domain.BrandRequestMother;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import co.crisi.productopolis.model.response.register.BrandResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;


class BrandMapperTest {

    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    private final IBrandFactory factory = new BrandFactory();

    @Test
    void mapToResponse() {
        var brand = factory.create(1L, "Perficient", "A great company");

        var response = mapper.map(brand);

        assertThat(response)
                .isNotNull()
                .extracting(BrandResponse::id, BrandResponse::name, BrandResponse::description)
                .contains(brand.getId(), brand.getName(), brand.getDescription());
    }

    @Test
    void mapToBrand() {
        var request = BrandRequestMother.random();

        var brand = mapper.map(request);
        assertThat(brand)
                .isNotNull()
                .extracting(IBrand::getId, IBrand::getName, IBrand::getDescription)
                .contains(brand.getId(), brand.getName(), brand.getDescription());
    }


}