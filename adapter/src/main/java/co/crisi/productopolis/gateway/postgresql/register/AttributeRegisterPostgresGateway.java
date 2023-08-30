package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.gateway.jpamodel.mapper.AttributeJpaMapper;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttributeRegisterPostgresGateway implements IAttributeRegisterGateway {

    private final AttributeJpaRepository repository;

    private final AttributeJpaMapper mapper = Mappers.getMapper(AttributeJpaMapper.class);

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IAttribute save(IAttribute entity) {
        var attributeJpaEntity = mapper.map(entity);
        var attributeSaved = repository.save(attributeJpaEntity);
        return mapper.map(attributeSaved);
    }

}
