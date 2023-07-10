package com.ellisonalves.thehotel.application.pojos.errors;

import java.util.List;

public record ErrorPerFiledList(List<ErrorsPerField> errors) {
}
