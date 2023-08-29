package co.crisi.productopolis.gateway.jpamodel.mapper;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.gateway.jpamodel.ProductAttributeJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.ProductJpaEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AttributeJpaMapper.class)
public interface ProductJpaMapper {

    AttributeJpaMapper ATTRIBUTE_JPA_MAPPER = Mappers.getMapper(AttributeJpaMapper.class);

    @Mapping(target = "attributes", expression = "java(mapToProductAttributeEntities(product.getAttributes()))")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    ProductJpaEntity map(IProduct product);

    default List<ProductAttributeJpaEntity> mapToProductAttributeEntities(List<IAttribute> attributes) {
        return attributes.stream().map(this::mapToProductAttributeEntity)
                .collect(Collectors.toList());
    }

    default ProductAttributeJpaEntity mapToProductAttributeEntity(IAttribute attribute) {
        return ProductAttributeJpaEntity
                .builder()
                .attribute(ATTRIBUTE_JPA_MAPPER.map(attribute))
                .value(attribute.getValue())
                .build();
    }

    @AfterMapping
    default void setProductReferenceToAttributeProduct(
            @MappingTarget
                    ProductJpaEntity productJpaEntity) {

        productJpaEntity.getAttributes()
                .forEach(a -> a.setProduct(productJpaEntity));
    }


}
