package com.ellisonalves.thehotel.interfaces.endpoints.shared;

import com.ellisonalves.thehotel.application.exceptions.handlers.GlobalExceptionHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UnitTestUtils {
    public static MockMvc createStandaloneSetup(Object... controllers) {
        return MockMvcBuilders.standaloneSetup(controllers).setControllerAdvice(new GlobalExceptionHandler()).build();
    }
}
