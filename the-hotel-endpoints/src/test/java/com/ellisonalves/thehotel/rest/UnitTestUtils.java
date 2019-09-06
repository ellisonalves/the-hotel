package com.ellisonalves.thehotel.rest;

import com.ellisonalves.thehotel.rest.application.exceptions.handlers.GlobalExceptionHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UnitTestUtils {
    public static MockMvc createStandaloneSetup(Object... controllers) {
        return MockMvcBuilders.standaloneSetup(controllers).setControllerAdvice(new GlobalExceptionHandler()).build();
    }
}
