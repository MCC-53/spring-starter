package com.nieceoftimes.serverside.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handleErrorException(ApiException apiException) {
        return new ResponseEntity<>(apiException.getErrorResponse(), apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException,
                                                                          HttpHeaders httpHeaders,
                                                                          HttpStatus httpStatus,
                                                                          WebRequest webRequest) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        "Method "
                                + httpRequestMethodNotSupportedException.getMethod()
                                + "not available!"
                ), httpStatus);
    }
}
