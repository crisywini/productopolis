package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.IAttributeDeleteBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeDeleteGateway;
import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.presenter.delete.IAttributeDeletePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class AttributeDeleteInteractorTest {

    @Mock
    private IAttributeDeletePresenter presenter;

    @Mock
    private IAttributeDeleteGateway gateway;

    private IAttributeDeleteBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new AttributeDeleteInteractor(presenter, gateway);
    }

    @Test
    void deleteByIdWhenExistingAttribute_shouldPrepareSuccessfulView() throws AttributeBusinessException {

        var id = 1L;
        given(gateway.existsById(id))
                .willReturn(true);
        given(presenter.prepareSuccessfulView(id))
                .willReturn(id);

        var deletedId = boundary.deleteById(id);

        verify(gateway).existsById(id);
        verify(presenter).prepareSuccessfulView(id);
        assertThat(deletedId)
                .isEqualTo(id);

    }

}