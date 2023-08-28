package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.domain.objectmother.CategoryMother;
import co.crisi.productopolis.gateway.jpamodel.mapper.CategoryJpaMapper;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class CategoryRegisterPostgresGatewayTest {

    @Mock
    private CategoryJpaRepository repository;

    @InjectMocks
    private CategoryRegisterPostgresGateway gateway;

    private final CategoryJpaMapper mapper = Mappers.getMapper(CategoryJpaMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsByName_whenExisting_shouldReturnTrue() {
        var name = "name";
        given(repository.existsByName(name))
                .willReturn(true);

        var existsByName = gateway.existsByName(name);

        assertThat(existsByName)
                .isTrue();
    }

    @Test
    void existsById_whenExisting_shouldReturnTrue() {
        var id = 1L;
        given(repository.existsById(id))
                .willReturn(true);

        var existsById = gateway.existsById(id);

        assertThat(existsById)
                .isTrue();
    }

    @Test
    void save() {
        var category = CategoryMother.random();
        var entity = mapper.map(category);
        given(repository.save(entity))
                .willReturn(entity);

        var entitySaved = gateway.save(category);

        assertThat(entitySaved)
                .isNotNull()
                .isEqualTo(category);
    }

}