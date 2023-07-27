package co.crisi.productopolis.gateways.postgresql;

import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.gateways.model.mapper.AttributeMapper;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AttributeRegisterPostgresSQLGateway implements IAttributeRegisterGateway {

    @Autowired
    private AttributeJpaRepository repository;

    private final AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void save(IAttribute entity) {
        var jpaEntity = mapper.mapToJpaEntityCreation(entity);
        repository.save(jpaEntity);
    }

}
