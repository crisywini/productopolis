package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.RepeatedAttributeException;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.register.IAttributeRegisterPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class AttributeRegisterInteractorTest {

    @Mock
    private IAttributeRegisterGateway gateway;

    @Mock
    private IAttributeRegisterPresenter presenter;

    private final IAttributeFactory factory = new AttributeFactory();

    private final AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);

    private IAttributeRegisterBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new AttributeRegisterInteractor(factory, presenter, gateway, mapper);
    }

    @Test
    void registerWhenNotExistingAttribute_shouldPrepareSuccessfulView() throws AttributeBusinessException {
        var id = 1L;
        var attribute = factory.create(id, "weight", "The weight of the product", "10 pounds");
        var request = mapper.mapToRequest(attribute);
        var attributeResponse = mapper.map(attribute);
        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareSuccessfulView(attributeResponse))
                .willReturn(attributeResponse);

        var response = boundary.create(request);

        verify(gateway).existsById(id);
        verify(gateway).save(attribute);
        verify(presenter).prepareSuccessfulView(attributeResponse);

        assertThat(response)
                .isNotNull()
                .isEqualTo(attributeResponse);
    }

    @Test
    void registerWhenExistingAttribute_shouldPrepareFailView() throws AttributeBusinessException {
        var id = 1L;
        var attribute = factory.create(id, "weight", "The weight of the product", "10 pounds");
        var request = mapper.mapToRequest(attribute);
        var throwable = new RepeatedAttributeException(
                String.format("The attribute with id %d already exists!", request.id()));
        given(gateway.existsById(id))
                .willReturn(true);
        given(presenter.prepareFailView(any(AttributeBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var attributeResponse = boundary.create(request);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(AttributeBusinessException.class));

        assertThat(response)
                .isNotNull()
                .isInstanceOf(RepeatedAttributeException.class)
                .isEqualTo(throwable);
    }


}