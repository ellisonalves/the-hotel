package com.ellisonalves.thehotel.application.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found exception");
    }
}
