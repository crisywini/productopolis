package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryExtractPostgresGateway implements ICategoryExtractGateway {

    @Autowired
    private CategoryJpaRepository repository;

    @Override
    public ICategory getById(Long aLong) {
        return null;
    }

    @Override
    public List<ICategory> getAll() {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
