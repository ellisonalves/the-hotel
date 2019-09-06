package com.ellisonalves.thehotel.rest.application.exceptions.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FieldMessages {
    private List<Messages> messages;
}
