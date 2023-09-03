package co.crisi.productopolis.exception;

public class IncorrectProductStockException extends ProductBusinessException {

    public IncorrectProductStockException(String errorMessage) {
        super(errorMessage);
    }

}
