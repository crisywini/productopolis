package co.crisi.productopolis.domain.exception;

public class NullFieldException extends RuntimeException{

    public NullFieldException(String errorMessage){
        super(errorMessage);
    }

}
