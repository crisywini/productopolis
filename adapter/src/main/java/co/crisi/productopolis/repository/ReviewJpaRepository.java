package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.ReviewJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewJpaRepository extends PagingAndSortingRepository<ReviewJpaEntity, Long>,
        CrudRepository<ReviewJpaEntity, Long> {

}
