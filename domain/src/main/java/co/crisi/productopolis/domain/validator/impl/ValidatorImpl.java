package co.crisi.productopolis.domain.validator.impl;

import co.crisi.productopolis.domain.validator.Validator;

public class ValidatorImpl implements Validator {

    @Override
    public <T> T validate(T object, String fieldName) {
        return object;
    }

}
