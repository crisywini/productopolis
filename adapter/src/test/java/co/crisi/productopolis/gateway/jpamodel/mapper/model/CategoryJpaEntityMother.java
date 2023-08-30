package co.crisi.productopolis.gateway.jpamodel.mapper.model;

import co.crisi.productopolis.gateway.jpamodel.CategoryJpaEntity;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryJpaEntityMother {

    public CategoryJpaEntity random(){
        return CategoryJpaEntity.builder()
                .id(1L)
                .name("books")
                .description("Books about books")
                .lastUpdate(LocalDate.now())
                .creationDate(LocalDate.now())
                .build();
    }

}
