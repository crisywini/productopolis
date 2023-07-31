package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.ICategoryDeleteBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryDeleteGateway;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.presenter.delete.ICategoryDeletePresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryDeleteInteractor implements ICategoryDeleteBoundary {

    private final ICategoryDeleteGateway gateway;

    private final ICategoryDeletePresenter presenter;

    @Override
    public Long deleteById(Long id) throws CategoryBusinessException {
        if (!gateway.existsById(id)) {
            return presenter.prepareFailView(
                    new CategoryNotFoundException(String.format("The category with id %d does not exists!", id)));
        }

        gateway.deleteById(id);

        return presenter.prepareSuccessfulView(id);
    }

}
