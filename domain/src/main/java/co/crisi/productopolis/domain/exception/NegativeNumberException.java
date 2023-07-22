package co.crisi.productopolis.domain.exception;

public class NegativeNumberException extends RuntimeException{

    public NegativeNumberException(String errorMessage){
        super(errorMessage);
    }

}
