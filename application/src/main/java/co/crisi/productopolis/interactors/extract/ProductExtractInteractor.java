package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.IProductExtractBoundary;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.extract.IProductExtractPresenter;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RequiredArgsConstructor
public class ProductExtractInteractor implements IProductExtractBoundary {

    private final IProductExtractGateway gateway;

    private final IProductExtractPresenter presenter;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductResponse getById(Long id) throws ProductBusinessException {
        var productEither = validateProductExistence(id);

        return productEither.isRight() ? presenter.prepareSuccessfulView(mapper.map(productEither.get())) :
                presenter.prepareFailView(productEither.getLeft());
    }

    @Override
    public List<ProductResponse> getAll() {
        return presenter.prepareSuccessfulView(mapper.map(gateway.getAll()));
    }

    private Either<ProductNotFoundException, IProduct> validateProductExistence(Long id) {
        return gateway.existsById(id) ? Either.right(gateway.getById(id))
                : Either.left(new ProductNotFoundException(String.format("The product with id %d does not exists!", id)));
    }
}
