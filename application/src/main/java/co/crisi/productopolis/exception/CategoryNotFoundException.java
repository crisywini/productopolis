package co.crisi.productopolis.exception;

public class CategoryNotFoundException extends CategoryBusinessException{

    public CategoryNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
