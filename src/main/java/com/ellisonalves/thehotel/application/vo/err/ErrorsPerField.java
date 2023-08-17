package com.ellisonalves.thehotel.application.vo.err;

import java.util.List;

public record ErrorsPerField(String fieldName, List<String> errorMessages) {

}
