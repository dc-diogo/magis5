package magis.stockchallenge.exception;

import magis.stockchallenge.enums.ErrorCode;

public class DrinkTypeInvalidException extends RuntimeException {

    public DrinkTypeInvalidException() {
        super(ErrorCode.DRINK_TYPE_INVALID.getMessage());
    }

    public DrinkTypeInvalidException(String message) {
        super(message);
    }
}
