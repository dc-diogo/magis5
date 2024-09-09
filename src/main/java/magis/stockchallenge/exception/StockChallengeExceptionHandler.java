package magis.stockchallenge.exception;

import jakarta.servlet.http.HttpServletRequest;
import magis.stockchallenge.factory.HttpErrorResponseFactory;
import magis.stockchallenge.gateway.model.response.HttpErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class StockChallengeExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DrinkTypeInvalidException.class})
    public ResponseEntity<HttpErrorResponse> handleDrinkTypeInvalidException(DrinkTypeInvalidException e, HttpServletRequest request) {
        HttpErrorResponse errorResponse = HttpErrorResponseFactory.build(
                "INVALID_DRINK_TYPE",
                e.getMessage(),
                List.of("Drink type not supported")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InsuficientSectorCapacityException.class})
    public ResponseEntity<HttpErrorResponse> handleNotEnoughCapacityException(InsuficientSectorCapacityException e, HttpServletRequest request) {
        HttpErrorResponse errorResponse = HttpErrorResponseFactory.build(
                "INSUFICIENT_CAPACITY",
                e.getMessage(),
                List.of("Insuficient capacity on sector")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorResponse> handleAllExceptions(Exception e, HttpServletRequest request) {
        HttpErrorResponse errorResponse = HttpErrorResponseFactory.build(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred",
                List.of(e.getMessage())
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
