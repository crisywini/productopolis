package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.IAttributeUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeUpdateGateway;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.model.request.AttributeUpdateRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.update.IAttributeUpdatePresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttributeUpdateInteractor implements IAttributeUpdateBoundary {

    private final IAttributeUpdateGateway gateway;

    private final IAttributeUpdatePresenter presenter;

    private final AttributeMapper mapper;

    private final IAttributeFactory factory = new AttributeFactory();


    @Override
    public AttributeResponse update(AttributeUpdateRequest request) throws AttributeBusinessException {

        if (!gateway.existsById(request.attributeId())) {
            return presenter.prepareFailView(new AttributeNotFoundException(
                    String.format("The attribute with id %d does not exists!", request.attributeId())));
        }

        var attributeInfo = factory.create(request.attributeId(), request.attributeInfo().name(),
                request.attributeInfo().description(), request.attributeInfo().value());

        var attributeUpdated = gateway.update(attributeInfo.getId(), attributeInfo);
        return presenter.prepareSuccessfulView(mapper.map(attributeUpdated));
    }

}
