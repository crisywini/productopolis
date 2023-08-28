package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.repository.BrandJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandExtractPostgresGateway implements IBrandExtractGateway {

    @Autowired
    private BrandJpaRepository repository;

    @Override
    public IBrand getById(Long id) {
        return null;
    }

    @Override
    public List<IBrand> getAll() {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
