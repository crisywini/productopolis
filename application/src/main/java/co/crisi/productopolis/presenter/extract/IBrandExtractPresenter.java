package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;
import java.util.List;

public interface IBrandExtractPresenter {

    BrandResponse prepareSuccessfulView(BrandResponse response);

    BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException;

    List<BrandResponse> prepareSuccessfulView(List<BrandResponse> response);

}
