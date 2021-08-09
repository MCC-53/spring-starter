package com.nieceoftimes.serverside.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private HttpStatus httpStatus;
    private ErrorResponse errorResponse;

    public ApiException(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorResponse = new ErrorResponse(message);
    }
}
