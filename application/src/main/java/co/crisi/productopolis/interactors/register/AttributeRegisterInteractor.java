package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.RepeatedAttributeException;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.register.IAttributeRegisterPresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttributeRegisterInteractor implements IAttributeRegisterBoundary {


    private final IAttributeFactory factory;

    private final IAttributeRegisterPresenter presenter;

    private final IAttributeRegisterGateway gateway;

    private final AttributeMapper mapper;

    @Override
    public AttributeResponse create(AttributeRequest request) throws AttributeBusinessException {

        if (gateway.existsByName(request.name())) {
            return presenter.prepareFailView(new RepeatedAttributeException(
                    String.format("The attribute with name %s already exists!", request.name())));
        }

        var attribute = factory.create(null, request.name(), request.description(), request.value());

        var savedAttribute = gateway.save(attribute);

        return presenter.prepareSuccessfulView(mapper.map(savedAttribute));
    }

}
