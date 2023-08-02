package co.crisi.productopolis.exception;

public class ProductNotFoundException extends ProductBusinessException{
    public ProductNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
