package com.ellisonalves.thehotel.application.exceptions.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private ErrorSeverity severity;
}
