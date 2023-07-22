package co.crisi.productopolis.domain.exception;

public class NotCorrectTypeException extends RuntimeException{

    public NotCorrectTypeException(String errorMessage){
        super(errorMessage);
    }

}
