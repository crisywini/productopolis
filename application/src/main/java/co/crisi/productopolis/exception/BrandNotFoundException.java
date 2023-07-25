package co.crisi.productopolis.exception;

public class BrandNotFoundException extends BrandBusinessException{

    public BrandNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
