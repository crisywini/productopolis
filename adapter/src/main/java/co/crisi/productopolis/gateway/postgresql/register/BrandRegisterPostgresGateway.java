package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.repository.BrandJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandRegisterPostgresGateway implements IBrandRegisterGateway {

    @Autowired
    private BrandJpaRepository repository;

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IBrand save(IBrand entity) {
        return null;
    }

}
