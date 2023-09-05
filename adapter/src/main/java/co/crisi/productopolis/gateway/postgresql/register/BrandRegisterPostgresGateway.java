package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.gateway.jpamodel.BrandJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.mapper.BrandJpaMapper;
import co.crisi.productopolis.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BrandRegisterPostgresGateway implements IBrandRegisterGateway {

    private final BrandJpaRepository repository;

    private final BrandJpaMapper mapper = Mappers.getMapper(BrandJpaMapper.class);

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IBrand save(IBrand entity) {
        var jpaEntity = mapper.map(entity);
        BrandJpaEntity save = repository.save(jpaEntity);
        return mapper.map(save);
    }

}
