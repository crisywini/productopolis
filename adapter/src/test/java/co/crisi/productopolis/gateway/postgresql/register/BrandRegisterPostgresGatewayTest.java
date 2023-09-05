package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.objectmother.BrandMother;
import co.crisi.productopolis.gateway.jpamodel.mapper.BrandJpaMapper;
import co.crisi.productopolis.repository.BrandJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class BrandRegisterPostgresGatewayTest {


    @Mock
    private BrandJpaRepository repository;

    @InjectMocks
    private BrandRegisterPostgresGateway gateway;

    private final BrandJpaMapper mapper = Mappers.getMapper(BrandJpaMapper.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsById_whenNoExistingEntity_shouldReturnFalse() {
        var id = 1L;

        given(repository.existsById(id))
                .willReturn(false);

        var exists = gateway.existsById(id);

        assertThat(exists)
                .isFalse();
    }

    @Test
    void existsById_whenExistingEntity_shouldReturnTrue() {
        var id = 1L;

        given(repository.existsById(id))
                .willReturn(true);

        var exists = gateway.existsById(id);

        assertThat(exists)
                .isTrue();
    }

    @Test
    void save() {
        var brand = BrandMother.random();
        var entity = mapper.map(brand);

        given(repository.save(entity))
                .willReturn(entity);

        var entitySaved = gateway.save(brand);

        assertThat(entitySaved)
                .isNotNull()
                .extracting(IBrand::getId, IBrand::getName, IBrand::getDescription)
                .contains(entitySaved.getId(), entitySaved.getName(), entitySaved.getDescription());
    }

}