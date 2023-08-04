package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.BrandJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandJpaRepository extends PagingAndSortingRepository<BrandJpaEntity, Long>,
        CrudRepository<BrandJpaEntity, Long> {

}
