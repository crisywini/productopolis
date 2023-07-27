package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.IAttributeDeleteBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeDeleteGateway;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.presenter.delete.IAttributeDeletePresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttributeDeleteInteractor implements IAttributeDeleteBoundary {

    private final IAttributeDeletePresenter presenter;

    private final IAttributeDeleteGateway gateway;


    @Override
    public Long deleteById(Long id) throws AttributeBusinessException {

        if (!gateway.existsById(id)) {
            return presenter.prepareFailView(
                    new AttributeNotFoundException(String.format("The attribute with id %d does not exists!", id)));
        }
        gateway.deleteById(id);

        return presenter.prepareSuccessfulView(id);
    }

}
