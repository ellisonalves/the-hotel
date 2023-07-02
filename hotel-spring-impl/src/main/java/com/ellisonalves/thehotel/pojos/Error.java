package com.ellisonalves.thehotel.pojos;

public record Error(
    String message,
    MessageSeverity severity
) {
}
