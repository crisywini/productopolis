package co.crisi.productopolis.gateway.postgresql.delete;

import co.crisi.productopolis.boundaries.output.IBrandDeleteGateway;
import co.crisi.productopolis.repository.BrandJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDeletePostgresGateway implements IBrandDeleteGateway {

    @Autowired
    private BrandJpaRepository repository;

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
