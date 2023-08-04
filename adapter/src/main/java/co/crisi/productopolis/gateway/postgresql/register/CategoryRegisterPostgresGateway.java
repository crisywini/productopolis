package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.ICategoryRegisterGateway;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRegisterPostgresGateway implements ICategoryRegisterGateway {

    @Autowired
    private CategoryJpaRepository repository;

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public ICategory save(ICategory entity) {
        return null;
    }

}
