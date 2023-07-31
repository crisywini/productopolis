package co.crisi.productopolis.exception;

public class CategoryRepeatedException extends CategoryBusinessException{

    public CategoryRepeatedException(String errorMessage) {
        super(errorMessage);
    }

}
