package co.crisi.productopolis.gateway.postgresql.delete;

import co.crisi.productopolis.boundaries.output.ICategoryDeleteGateway;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDeletePostgresGateway implements ICategoryDeleteGateway {

    @Autowired
    private CategoryJpaRepository repository;

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
