package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.ICategoryUpdateGateway;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryUpdatePostgresGateway implements ICategoryUpdateGateway {

    @Autowired
    private CategoryJpaRepository repository;

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public ICategory update(Long aLong, ICategory newInfo) {
        return null;
    }

}
