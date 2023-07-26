package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.IAttributeExtractBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.domain.AttributeResponseMother;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.model.response.AttributeResponse;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.extract.IAttributeExtractPresenter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

class AttributeExtractInteractorTest {

    @Mock
    private IAttributeExtractPresenter presenter;

    @Mock
    private IAttributeExtractGateway gateway;

    private final AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);

    private IAttributeExtractBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new AttributeExtractInteractor(presenter, gateway, mapper);
    }

    @Test
    void getByIdWhenExistingAttribute_shouldPrepareSuccessfulView() throws AttributeBusinessException {
        var responseExpected = AttributeResponseMother.random();
        var id = responseExpected.id();

        given(gateway.existsById(id))
                .willReturn(true);
        given(gateway.getById(id))
                .willReturn(mapper.mapToAttribute(responseExpected));
        given(presenter.prepareSuccessfulView(responseExpected))
                .willReturn(responseExpected);

        var response = boundary.getById(id);

        verify(gateway).existsById(id);
        verify(gateway).getById(id);

        assertThat(response)
                .isNotNull()
                .isEqualTo(responseExpected);


    }

    @Test
    void getByIdWhenNonExistingAttribute_shouldPrepareFailView() throws AttributeBusinessException {
        var responseExpected = AttributeResponseMother.random();
        var id = responseExpected.id();
        var throwable = new AttributeNotFoundException(String.format("The attribute with id %d was not found!", id));
        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareFailView(any(AttributeBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var attributeResponse = boundary.getById(id);
        });

        verify(gateway).existsById(id);

        assertThat(response)
                .isNotNull()
                .isInstanceOf(AttributeNotFoundException.class)
                .isEqualTo(throwable);
    }

    @Test
    void getAll_shouldPrepareSuccessfulView() {
        IAttribute a = mapper.mapToAttribute(AttributeResponseMother.random());
        IAttribute b = mapper.mapToAttribute(AttributeResponseMother.random());
        var attributes = new ArrayList<IAttribute>();
        attributes.add(a);
        attributes.add(b);

        var expected = mapper.map(attributes);

        given(gateway.getAll())
                .willReturn(attributes);

        given(presenter.prepareSuccessfulView(expected))
                .willReturn(expected);
        var response = boundary.getAll();

        verify(gateway).getAll();
        verify(presenter).prepareSuccessfulView(expected);
        assertThat(response)
                .isNotNull()
                .isEqualTo(expected);
    }

}