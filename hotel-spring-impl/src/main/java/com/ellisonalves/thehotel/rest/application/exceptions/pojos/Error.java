package com.ellisonalves.thehotel.rest.application.exceptions.pojos;

public record Error(
    String message,
    MessageSeverity severity
) {
}
