package co.crisi.productopolis.domain.exception;

public class NegativeNumberException extends DomainException{

    public NegativeNumberException(String errorMessage){
        super(errorMessage);
    }

}
