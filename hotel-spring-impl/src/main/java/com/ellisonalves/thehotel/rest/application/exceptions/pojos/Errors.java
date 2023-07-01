package com.ellisonalves.thehotel.rest.application.exceptions.pojos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Errors {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    private final List<Error> errors;

    public Errors(String message, MessageSeverity error) {
        this.errors = Arrays.asList(new Error(message, error));
    }

    public Errors(String field, List<Error> errors) {
        this.field = field;
        this.errors = errors;

    }

    public String getField() {
        return field;
    }

    public List<Error> getErrors() {
        return errors;
    }

}
