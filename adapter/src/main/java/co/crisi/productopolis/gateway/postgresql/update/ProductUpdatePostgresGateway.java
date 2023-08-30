package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.gateway.jpamodel.mapper.ProductJpaMapper;
import co.crisi.productopolis.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class ProductUpdatePostgresGateway implements IProductUpdateGateway {

    private final ProductJpaRepository repository;

    private final ProductJpaMapper mapper = Mappers.getMapper(ProductJpaMapper.class);

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IProduct update(Long id, IProduct newInfo) {
        var entity = mapper.map(newInfo);
        entity.setId(id);
        var entityUpdated = repository.save(entity);
        return mapper.map(entityUpdated);
    }

}
