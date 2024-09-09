package magis.stockchallenge.factory;

import magis.stockchallenge.enums.ErrorCode;
import magis.stockchallenge.gateway.model.response.HttpErrorResponse;

import java.util.ArrayList;
import java.util.List;

public class HttpErrorResponseFactory {

    private HttpErrorResponseFactory() {
        throw new UnsupportedOperationException("Do not instantiate this class, use statically.");
    }

    public static HttpErrorResponse build(String errorCode, String message) {
        return HttpErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }

    public static HttpErrorResponse build(ErrorCode errorCode) {
        return HttpErrorResponse.builder()
                .errorCode(errorCode.toString())
                .message(errorCode.getMessage())
                .errors(new ArrayList<>())
                .build();
    }

    public static HttpErrorResponse build(String errorCode, String message, List<String> errors) {
        return HttpErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .errors(errors)
                .build();
    }



}
