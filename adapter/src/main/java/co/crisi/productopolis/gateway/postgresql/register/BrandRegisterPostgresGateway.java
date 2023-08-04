package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.repository.BrandJpaRepository;

public class BrandRegisterPostgresGateway implements IBrandRegisterGateway {

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
