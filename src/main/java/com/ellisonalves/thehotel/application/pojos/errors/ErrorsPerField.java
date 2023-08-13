package com.ellisonalves.thehotel.application.pojos.errors;

import java.util.List;

public record ErrorsPerField(String fieldName, List<String> errorMessages) {

}
