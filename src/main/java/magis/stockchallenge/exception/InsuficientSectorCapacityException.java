package magis.stockchallenge.exception;

import magis.stockchallenge.enums.ErrorCode;

public class InsuficientSectorCapacityException extends RuntimeException {
    public InsuficientSectorCapacityException() {
        super(ErrorCode.NOT_ENOUGH_CAPACITY.getMessage());
    }

    public InsuficientSectorCapacityException(String message) {
        super(message);
    }
}
