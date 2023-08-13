package com.ellisonalves.thehotel.infrastructure.spring.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ellisonalves.thehotel.annotations.ContractTest;
import com.ellisonalves.thehotel.application.usecases.ManageGuestUseCase;

@ContractTest
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ManageGuestUseCase guestService;

    @Test
    void findByIdInvalid() throws Exception {
        mockMvc.perform(get("/guests"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}