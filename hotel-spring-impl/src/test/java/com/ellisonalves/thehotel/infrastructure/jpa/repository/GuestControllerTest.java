package com.ellisonalves.thehotel.infrastructure.jpa.repository;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ellisonalves.thehotel.rest.application.exceptions.pojos.MessageSeverity;
import com.ellisonalves.thehotel.rest.endpoints.CustomerController;

@Disabled
@ExtendWith(MockitoExtension.class)
public class GuestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    // @Mock
    // private ManageGuestUseCase guestService;

    @BeforeEach
    public void setup() {
        // mockMvc = UnitTestUtils.createStandaloneSetup(customerController);
    }

    @Test
    public void findByIdInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].severity",
                        CoreMatchers.is(MessageSeverity.ERROR.toString())));
    }

    @Test
    public void findOneThrowsBadRequestWhenCustomerDoesNotExist() throws Exception {
        // Mockito.when(guestService.findOne(Mockito.any())).thenThrow(new
        // ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].severity",
                        CoreMatchers.is(MessageSeverity.ERROR.toString())));
    }
}