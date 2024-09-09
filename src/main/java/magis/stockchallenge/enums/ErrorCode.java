package magis.stockchallenge.enums;

public enum ErrorCode {

    BAD_REQUEST("One or more parameters are incorrect"),
    DRINK_TYPE_INVALID("Drink type not supported"),
    DRINK_VOLUME_EXCEED("Volume for the inserted drink exceed the permited."),
    INVALID_SECTOR_ID("The sector could not be found, please review sectorId and try again"),
    DRINK_TYPE_MISMATCH("The drink does not match the drinkType in the sector"),
    NOT_ENOUGH_CAPACITY("The sector does not have enough space to stock the requested volume");

    private String error;

    ErrorCode(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.error;
    }

}
