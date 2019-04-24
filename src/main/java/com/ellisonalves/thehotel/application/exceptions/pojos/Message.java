package com.ellisonalves.thehotel.application.exceptions.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String message;
    private MessageSeverity severity;
}
