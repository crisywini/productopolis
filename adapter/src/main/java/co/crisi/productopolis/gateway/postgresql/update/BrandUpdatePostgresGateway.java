package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.IBrandUpdateGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.gateway.jpamodel.mapper.BrandJpaMapper;
import co.crisi.productopolis.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class BrandUpdatePostgresGateway implements IBrandUpdateGateway {

    private final BrandJpaRepository repository;

    private final BrandJpaMapper mapper = Mappers.getMapper(BrandJpaMapper.class);

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IBrand update(Long id, IBrand newInfo) {
        var entity = mapper.map(newInfo);
        var entityUpdated = repository.save(entity);
        return mapper.map(entityUpdated);
    }

}
