package com.ellisonalves.thehotel.application.pojos;

import java.util.List;

public record ErrorsPerField(String fieldName, List<String> errorMessages) {

}
