package co.crisi.productopolis.exception;

import co.crisi.productopolis.domain.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public @ResponseBody
    ResponseEntity<String> handleDomainException(DomainException domainException) {
        return new ResponseEntity<>(domainException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
