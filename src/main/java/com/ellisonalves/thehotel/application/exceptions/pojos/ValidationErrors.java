package com.ellisonalves.thehotel.application.exceptions.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrors {
    private List<FieldErrorMessages> validationErrors;
}
