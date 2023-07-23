package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.IReview;
import co.crisi.productopolis.domain.Review;
import co.crisi.productopolis.domain.factory.IReviewFactory;
import java.time.LocalDate;

public class ReviewFactory implements IReviewFactory {

    @Override
    public IReview create(Long id, Integer rating, String message, IProduct product, Long userId) {
        return new Review(id, rating, message, product, userId, LocalDate.now());
    }

}
