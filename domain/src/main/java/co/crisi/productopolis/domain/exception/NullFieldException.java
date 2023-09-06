package co.crisi.productopolis.domain.exception;

public class NullFieldException extends DomainException{

    public NullFieldException(String errorMessage){
        super(errorMessage);
    }

}
