package com.ellisonalves.thehotel.application.vo.err;

import java.util.Arrays;
import java.util.List;

public record ErrorMessageList(List<ErrorMessage> errors) {

    public static ErrorMessageList createSingleErrorMessageList(String message) {
        return new ErrorMessageList(Arrays.asList(new ErrorMessage(message)));
    }

}
