package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.ImageJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpaRepository extends PagingAndSortingRepository<ImageJpaEntity, Long>,
        CrudRepository<ImageJpaEntity, Long> {

}
