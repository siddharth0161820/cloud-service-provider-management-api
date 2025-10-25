package com.cloudvendor.cloudvendor.GlobalExceptionHandling;

public class NOTFOUNDEXCEPTION extends RuntimeException{
    //1> Constructor

    public NOTFOUNDEXCEPTION(String message) {
        super(message);
    }

    public NOTFOUNDEXCEPTION(String message, Throwable cause) {
        super(message, cause);
    }
}
