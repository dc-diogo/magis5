package magis.stockchallenge.exception;

import magis.stockchallenge.enums.ErrorCode;

public class InvalidSectorIdException extends RuntimeException {
    public InvalidSectorIdException() {
        super(ErrorCode.INVALID_SECTOR_ID.getMessage());
    }

    public InvalidSectorIdException(String message) {
        super(message);
    }
}
