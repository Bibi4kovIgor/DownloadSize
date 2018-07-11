package com.main.exceptions;

public class ErrorReadingAddressException extends RuntimeException {

    /**
     * Custom Exception for handling reading address errors
     *
     * @param message   Text of the Exception
     * @param cause     The Exception type (may be nullable)
     * @param enableSuppression Enable/disable exception suppression
     * @param writableStackTrace Print or not stack tracer to the user
     * cause, enableSuppression, writableStackTrace
     * */
    public ErrorReadingAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

