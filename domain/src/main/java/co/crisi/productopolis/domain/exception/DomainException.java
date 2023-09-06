package co.crisi.productopolis.domain.exception;


public class DomainException extends RuntimeException{

    public DomainException(String errorMessage){
        super(errorMessage);
    }

}
