package com.ellisonalves.thehotel.rest.endpoints;

import com.ellisonalves.thehotel.domain.services.GuestService;
import com.ellisonalves.thehotel.rest.UnitTestUtils;
import com.ellisonalves.thehotel.rest.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.rest.application.exceptions.pojos.MessageSeverity;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(MockitoJUnitRunner.class)
public class GuestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private GuestService guestService;

    @Before
    public void setup() {
        mockMvc = UnitTestUtils.createStandaloneSetup(customerController);
    }

    @Test
    public void findByIdInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].severity", CoreMatchers.is(MessageSeverity.ERROR.toString())));
    }

    @Test
    public void findOneThrowsBadRequestWhenCustomerDoesNotExist() throws Exception {
        Mockito.when(guestService.findOne(Mockito.anyLong())).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].severity", CoreMatchers.is(MessageSeverity.ERROR.toString())));
    }
}