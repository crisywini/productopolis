package co.crisi.productopolis.repository;

import co.crisi.productopolis.gateway.jpamodel.CategoryJpaEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends PagingAndSortingRepository<CategoryJpaEntity, Long>,
        CrudRepository<CategoryJpaEntity, Long> {

    boolean existsByName(String name);


}
