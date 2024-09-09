package magis.stockchallenge.exception;

import magis.stockchallenge.enums.ErrorCode;

public class DrinkVolumeExceedException extends RuntimeException {
    public DrinkVolumeExceedException() {
        super(ErrorCode.DRINK_VOLUME_EXCEED.getMessage());
    }

    public DrinkVolumeExceedException(String message) {
        super(message);
    }
}
