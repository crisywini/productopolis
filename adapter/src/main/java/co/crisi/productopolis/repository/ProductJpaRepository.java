package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.ProductJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends PagingAndSortingRepository<ProductJpaEntity, Long>,
        CrudRepository<ProductJpaEntity, Long> {

}
