package co.crisi.productopolis.domain.exception;

public class EmptyStringException extends RuntimeException{

    public EmptyStringException(String errorMessage){
        super(errorMessage);
    }

}
