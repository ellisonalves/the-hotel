package com.ellisonalves.thehotel.application.pojos;

import java.util.List;

public class ErrorMessage {

    private String message;
    private List<Error> errors;
    private MessageSeverity error;

    public ErrorMessage(String message, List<Error> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ErrorMessage(String message, MessageSeverity error) {
        this.message = message;
        this.error = error;
    }

}
