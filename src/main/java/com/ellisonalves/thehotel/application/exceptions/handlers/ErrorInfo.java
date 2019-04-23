package com.ellisonalves.thehotel.application.exceptions.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {
    private String message;
    private Severity severity;

    public enum Severity {
        INFO, WARNING, ERROR
    }
}
