package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.IBrandUpdateGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.repository.BrandJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandUpdatePostgresGateway implements IBrandUpdateGateway {

    @Autowired
    private BrandJpaRepository repository;

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public IBrand update(Long aLong, IBrand newInfo) {
        return null;
    }

}
