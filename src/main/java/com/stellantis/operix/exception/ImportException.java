package com.stellantis.operix.exception;

public class ImportException
        extends RuntimeException {
    public ImportException(String message) {
        super(message);
    }
    public ImportException(String msg, Throwable cause) {
        super(msg, cause);
    }
}