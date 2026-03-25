package com.stellantis.operix.exception;

public class ResourceNotFoundException
        extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}