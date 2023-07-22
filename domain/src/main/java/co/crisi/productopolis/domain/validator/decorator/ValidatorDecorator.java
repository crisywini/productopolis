package co.crisi.productopolis.domain.validator.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ValidatorDecorator implements Validator {

    protected Validator objectValidator;

    @Override
    public <T> T validate(T object, String fieldName) {
        return this.objectValidator.validate(object, fieldName);
    }

}
