package com.ellisonalves.thehotel.infrastructure.controller.guest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ellisonalves.thehotel.application.usecases.ManageGuestUseCase;
import com.ellisonalves.thehotel.infrastructure.config.MessagesConfig;

@WebMvcTest({GuestController.class, MessagesConfig.class})
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManageGuestUseCase guestService;

    @Test
    void findByIdInvalid() throws Exception {
        mockMvc.perform(get("/guests"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}