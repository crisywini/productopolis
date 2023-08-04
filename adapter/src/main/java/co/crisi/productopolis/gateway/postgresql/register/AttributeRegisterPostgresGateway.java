package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeRegisterPostgresGateway implements IAttributeRegisterGateway {

    @Autowired
    private AttributeJpaRepository repository;

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public IAttribute save(IAttribute entity) {
        return null;
    }

}
