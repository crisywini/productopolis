package co.crisi.productopolis.domain.exception;

public class NotCorrectTypeException extends DomainException{

    public NotCorrectTypeException(String errorMessage){
        super(errorMessage);
    }

}
