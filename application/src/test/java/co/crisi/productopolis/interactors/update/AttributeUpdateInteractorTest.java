package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.IAttributeUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeUpdateGateway;
import co.crisi.productopolis.domain.AttributeUpdateRequestMother;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.update.IAttributeUpdatePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class AttributeUpdateInteractorTest {

    @Mock
    private IAttributeUpdateGateway gateway;

    @Mock
    private IAttributeUpdatePresenter presenter;

    private AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);

    private IAttributeFactory factory = new AttributeFactory();

    private IAttributeUpdateBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new AttributeUpdateInteractor(gateway, presenter, mapper);
    }

    @Test
    void updateWhenExistingAttribute_shouldPrepareSuccessfulView() throws AttributeBusinessException {
        var request = AttributeUpdateRequestMother.random();
        var attribute = factory.create(request.attributeId(), request.attributeInfo().name(),
                request.attributeInfo().description(), request.attributeInfo().value());
        var expectedResponse = mapper.map(attribute);
        given(gateway.existsById(request.attributeId()))
                .willReturn(true);

        given(gateway.update(request.attributeId(), attribute))
                .willReturn(attribute);

        given(presenter.prepareSuccessfulView(expectedResponse))
                .willReturn(expectedResponse);

        var response = boundary.update(request);
        verify(gateway).existsById(request.attributeId());
        verify(gateway).update(request.attributeId(), attribute);
        verify(presenter).prepareSuccessfulView(expectedResponse);
        assertThat(response)
                .isEqualTo(expectedResponse);
    }

    @Test
    void updateWhenNonExistingAttribute_shouldPrepareFailView() throws AttributeBusinessException {
        var request = AttributeUpdateRequestMother.random();
        var throwable = new AttributeNotFoundException(
                String.format("The attribute with id %d does not exists!", request.attributeId()));
        given(gateway.existsById(request.attributeId()))
                .willReturn(false);

        given(presenter.prepareFailView(any(AttributeBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var attributeResponse = boundary.update(request);
        });
        verify(gateway).existsById(request.attributeId());
        verify(presenter).prepareFailView(any(AttributeBusinessException.class));

        assertThat(response)
                .isInstanceOf(AttributeNotFoundException.class)
                .isEqualTo(throwable);
    }

}