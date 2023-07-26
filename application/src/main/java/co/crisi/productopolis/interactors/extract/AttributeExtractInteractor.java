package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.IAttributeExtractBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.model.response.AttributeResponse;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.extract.IAttributeExtractPresenter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttributeExtractInteractor implements IAttributeExtractBoundary {

    private final IAttributeExtractPresenter presenter;

    private final IAttributeExtractGateway gateway;

    private final AttributeMapper mapper;

    @Override
    public AttributeResponse getById(Long id) throws AttributeBusinessException {

        if (!gateway.existsById(id)) {
            return presenter.prepareFailView(
                    new AttributeNotFoundException(String.format("The attribute with id %d was not found!", id)));
        }

        var attribute = gateway.getById(id);
        return presenter.prepareSuccessfulView(mapper.map(attribute));
    }

    @Override
    public List<AttributeResponse> getAll() {
        return presenter.prepareSuccessfulView(mapper.map(gateway.getAll()));
    }

}
