package com.ellisonalves.thehotel.rest.application.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found exception");
    }
}
