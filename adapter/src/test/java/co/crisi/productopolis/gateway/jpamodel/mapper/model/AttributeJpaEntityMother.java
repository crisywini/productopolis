package co.crisi.productopolis.gateway.jpamodel.mapper.model;

import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AttributeJpaEntityMother {

    public AttributeJpaEntity random() {
        return AttributeJpaEntity.builder()
                .id(1L)
                .name("weight")
                .description("The weight of the product!")
                .build();
    }

}
