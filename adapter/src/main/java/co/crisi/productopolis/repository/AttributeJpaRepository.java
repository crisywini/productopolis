package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeJpaRepository extends PagingAndSortingRepository<AttributeJpaEntity, Long>,
        CrudRepository<AttributeJpaEntity, Long> {

    boolean existsByName(String name);

}
