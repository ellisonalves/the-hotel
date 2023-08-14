package com.ellisonalves.thehotel.application.vo.err;

import java.util.List;

public record ErrorPerFiledList(List<ErrorsPerField> errors) {
}
