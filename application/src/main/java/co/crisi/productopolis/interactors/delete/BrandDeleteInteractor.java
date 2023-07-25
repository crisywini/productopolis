package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.IBrandDeleteBoundary;
import co.crisi.productopolis.boundaries.output.IBrandDeleteGateway;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.presenter.delete.IBrandDeletePresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandDeleteInteractor implements IBrandDeleteBoundary {

    private final IBrandDeleteGateway gateway;

    private final IBrandDeletePresenter presenter;

    @Override
    public Long deleteById(Long id) throws BrandBusinessException {

        if (!gateway.existsById(id)) {
            return presenter.prepareFailView(
                    new BrandNotFoundException(String.format("The brand with id %d was not found!", id)));
        }

        gateway.deleteById(id);
        return presenter.prepareSuccessfulView(id);
    }

}
