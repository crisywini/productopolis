package co.crisi.productopolis.exception;

public class RepeatedProductException extends ProductBusinessException{

    public RepeatedProductException(String errorMessage) {
        super(errorMessage);
    }

}
