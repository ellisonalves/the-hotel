package com.ellisonalves.thehotel.application.pojos;

import java.util.List;

public class ErrorMessages {

    private String field;
    private List<String> errors;

    public ErrorMessages(String field, List<String> errors) {
        this.field = field;
        this.errors = errors;
    }

}
