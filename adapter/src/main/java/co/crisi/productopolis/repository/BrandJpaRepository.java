package co.crisi.productopolis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandJpaRepository extends PagingAndSortingRepository<BrandJpaRepository, Long>,
        CrudRepository<BrandJpaRepository, Long> {

}
