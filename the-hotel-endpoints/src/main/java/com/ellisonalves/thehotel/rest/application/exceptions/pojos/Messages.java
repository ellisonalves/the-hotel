package com.ellisonalves.thehotel.rest.application.exceptions.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Messages {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    private List<Message> messages;

    public Messages(String message, MessageSeverity error) {
        this.messages = Arrays.asList(new Message(message, error));
    }

}
