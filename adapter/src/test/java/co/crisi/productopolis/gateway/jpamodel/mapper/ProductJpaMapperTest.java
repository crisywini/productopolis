package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.CategoryJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.ProductAttributeJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.ProductJpaEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductJpaMapperTest {

    private final ProductJpaMapper mapper = Mappers.getMapper(ProductJpaMapper.class);

    @Test
    void mapToJpaEntity() {
        var product = ProductMother.randomWithAttributesAndCategories();
        var attribute = product.getAttributes().get(0);
        var category = product.getCategories().get(0);

        var entity = mapper.map(product);

        assertThat(entity)
                .isNotNull()
                .extracting(ProductJpaEntity::getId, ProductJpaEntity::getName, ProductJpaEntity::getDescription,
                        ProductJpaEntity::getPrice, ProductJpaEntity::getStock)
                .contains(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                        product.getStock());
        var productAttributeEntity = entity.getAttributes().get(0);
        assertThat(productAttributeEntity)
                .isNotNull()
                .extracting(ProductAttributeJpaEntity::getValue, p -> p.getAttribute().getName(),
                        p -> p.getAttribute().getDescription(), p -> p.getAttribute().getId())
                .contains(attribute.getValue(), attribute.getName(), attribute.getDescription(), attribute.getId());
        var categoryJpaEntity = entity.getCategories().get(0);
        assertThat(categoryJpaEntity)
                .isNotNull()
                .extracting(CategoryJpaEntity::getId, CategoryJpaEntity::getName, CategoryJpaEntity::getDescription,
                        CategoryJpaEntity::getCreationDate, CategoryJpaEntity::getLastUpdate)
                .contains(category.getId(), category.getName(), category.getDescription(), category.getCreationDate(),
                        category.getLastUpdated());
    }


    @Test
    void mapToProductAttributeEntities() {
    }

    @Test
    void mapToProductAttributeEntity() {
    }

}