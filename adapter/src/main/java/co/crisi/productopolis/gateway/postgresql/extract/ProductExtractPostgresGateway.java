package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.gateway.jpamodel.mapper.ProductJpaMapper;
import co.crisi.productopolis.repository.ProductJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductExtractPostgresGateway implements IProductExtractGateway {

    private final ProductJpaRepository repository;

    private final ProductJpaMapper mapper = Mappers.getMapper(ProductJpaMapper.class);

    @Override
    public IProduct getById(Long id) {
        var product = repository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("The product with id %d was not found!", id)));
        return mapper.map(product);
    }

    @Override
    public List<IProduct> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
