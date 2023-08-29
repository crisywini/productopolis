package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.gateway.jpamodel.mapper.ProductJpaMapper;
import co.crisi.productopolis.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRegisterPostgresGateway implements IProductRegisterGateway {

    private final ProductJpaRepository repository;

    private final ProductJpaMapper mapper = Mappers.getMapper(ProductJpaMapper.class);


    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IProduct save(IProduct entity) {
        var productJpaEntity = mapper.map(entity);
        var productSaved = repository.save(productJpaEntity);
        return mapper.map(productSaved);
    }

}
