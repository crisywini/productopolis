package co.crisi.productopolis.domain.exception;

public class IllegalDateException extends RuntimeException {

    public IllegalDateException(String errorMessage){
        super(errorMessage);
    }

}
