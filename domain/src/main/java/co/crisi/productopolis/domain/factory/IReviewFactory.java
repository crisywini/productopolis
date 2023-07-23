package co.crisi.productopolis.domain.factory;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.IReview;

public interface IReviewFactory {

    IReview create(Long id, Integer rating, String message, IProduct product, Long userId);

}
