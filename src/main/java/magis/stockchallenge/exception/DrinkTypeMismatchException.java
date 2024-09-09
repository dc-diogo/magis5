package magis.stockchallenge.exception;

import magis.stockchallenge.enums.ErrorCode;

public class DrinkTypeMismatchException extends RuntimeException {
    public DrinkTypeMismatchException() {
        super(ErrorCode.DRINK_TYPE_MISMATCH.getMessage());
    }

    public DrinkTypeMismatchException(String message) {
        super(message);
    }
}
