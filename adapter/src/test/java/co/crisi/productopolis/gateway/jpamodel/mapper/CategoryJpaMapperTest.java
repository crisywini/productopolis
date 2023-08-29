package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.Category;
import co.crisi.productopolis.domain.objectmother.CategoryMother;
import co.crisi.productopolis.gateway.jpamodel.CategoryJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.mapper.model.CategoryJpaEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryJpaMapperTest {

    private final CategoryJpaMapper mapper = Mappers.getMapper(CategoryJpaMapper.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void mapToCategoryJpaEntity() {
        var category = CategoryMother.random();

        var entity = mapper.map(category);

        assertThat(entity)
                .isNotNull()
                .extracting(CategoryJpaEntity::getId, CategoryJpaEntity::getName, CategoryJpaEntity::getDescription,
                        CategoryJpaEntity::getCreationDate, CategoryJpaEntity::getLastUpdate)
                .contains(category.getId(), category.getName(), category.getDescription(), category.getCreationDate(),
                        category.getLastUpdated());
    }

    @Test
    void mapToCategory() {

        var entity = CategoryJpaEntityMother.random();

        var category = mapper.map(entity);

        assertThat(category)
                .isNotNull()
                .extracting(Category::getId, Category::getName, Category::getDescription, Category::getCreationDate,
                        Category::getLastUpdated)
                .contains(entity.getId(), entity.getName(), entity.getDescription(), entity.getCreationDate(),
                        entity.getLastUpdate());
    }

}