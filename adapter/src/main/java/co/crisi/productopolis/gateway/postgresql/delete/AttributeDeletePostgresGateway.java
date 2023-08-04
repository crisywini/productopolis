package co.crisi.productopolis.gateway.postgresql.delete;

import co.crisi.productopolis.boundaries.output.IAttributeDeleteGateway;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeDeletePostgresGateway implements IAttributeDeleteGateway {

    @Autowired
    private AttributeJpaRepository repository;

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
