package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.IProductDeleteBoundary;
import co.crisi.productopolis.boundaries.output.IProductDeleteGateway;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.presenter.delete.IProductDeletePresenter;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductDeleteInteractor implements IProductDeleteBoundary {

    private final IProductDeleteGateway gateway;

    private final IProductDeletePresenter presenter;

    @Override
    public Long deleteById(Long id) throws ProductBusinessException {
        var idFoundEither = validateProductExistence(id);
        if (idFoundEither.isLeft()) {
            return presenter.prepareFailView(idFoundEither.getLeft());
        }
        gateway.deleteById(idFoundEither.get());
        return presenter.prepareSuccessfulView(idFoundEither.get());
    }

    private Either<ProductBusinessException, Long> validateProductExistence(Long id) {
        return gateway.existsById(id) ? Either.right(id) :
                Either.left(new ProductNotFoundException(String.format("The product with id %d was not found!", id)));
    }
}
