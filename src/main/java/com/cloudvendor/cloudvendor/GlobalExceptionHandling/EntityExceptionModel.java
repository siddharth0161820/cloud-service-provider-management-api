package com.cloudvendor.cloudvendor.GlobalExceptionHandling;

import org.springframework.http.HttpStatus;

public class EntityExceptionModel {
    //1>Attributes
    private final String message;
    private final HttpStatus httpStatus;

    //2>Constructor
    public EntityExceptionModel(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    //3>Getter

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
