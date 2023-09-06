package co.crisi.productopolis.domain.exception;

public class EmptyStringException extends DomainException{

    public EmptyStringException(String errorMessage){
        super(errorMessage);
    }

}
