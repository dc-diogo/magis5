package magis.stockchallenge.exception;

import magis.stockchallenge.enums.ErrorCode;

public class InsuficientVolumeException extends RuntimeException {

    public InsuficientVolumeException() {
        super(ErrorCode.DRINK_TYPE_INVALID.getMessage());
    }

    public InsuficientVolumeException(String message) {
        super(message);
    }
}
