package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.ProductJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductJpaRepository extends PagingAndSortingRepository<ProductJpaEntity, Long>,
        CrudRepository<ProductJpaEntity, Long> {

}
