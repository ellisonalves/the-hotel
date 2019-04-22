package com.ellisonalves.thehotel.interfaces.endpoints;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CustomerControllerITCase extends AbstractIntegrationTestController {

    @Test
    public void shouldReadAllCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].id", CoreMatchers.is(1)));
    }

    @Test
    public void shouldReadOneCustomerForAGivenId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1)));
    }
}