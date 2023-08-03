package co.crisi.productopolis.domain;

import co.crisi.productopolis.model.request.ProductRequest;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ProductRequestMother {

    public ProductRequest random() {
        return ProductRequest.builder()
                .id(1L)
                .brandId(1L)
                .categoryIds(List.of(1L, 2L))
                .attributeIds(List.of(3L, 2L))
                .price(6000000.0)
                .stock(1000000L)
                .name("The name of the Wind")
                .description("an Ipad for students")
                .isActive(true)
                .isFeatured(true)
                .build();
    }

}
