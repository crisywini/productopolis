package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateways.model.AttributeJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttributeJpaRepository extends PagingAndSortingRepository<AttributeJpaEntity,Long>,
        CrudRepository<AttributeJpaEntity, Long> {

}
