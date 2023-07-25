package co.crisi.productopolis.exception;

public class RepeatedBrandException extends BrandBusinessException{

    public RepeatedBrandException(String errorMessage) {
        super(errorMessage);
    }

}
