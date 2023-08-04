package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.IAttributeUpdateGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeUpdatePostgresGateway implements IAttributeUpdateGateway {

    @Autowired
    private AttributeJpaRepository repository;

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public IAttribute update(Long aLong, IAttribute newInfo) {
        return null;
    }

}
