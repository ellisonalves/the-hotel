package com.ellisonalves.thehotel.interfaces.endpoints;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.application.exceptions.handlers.ErrorInfo;
import com.ellisonalves.thehotel.domain.services.RoomService;
import com.ellisonalves.thehotel.interfaces.endpoints.shared.UnitTestUtils;
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
public class RoomControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    @Before
    public void setup() {
        mockMvc = UnitTestUtils.createStandaloneSetup(roomController);
    }

    @Test
    public void findByIdInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.severity", CoreMatchers.is(ErrorInfo.Severity.ERROR.toString())));
    }

    @Test
    public void findOneThrowsBadRequestWhenCustomerDoesNotExist() throws Exception {
        Mockito.when(roomService.findOne(Mockito.anyInt())).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/100"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.severity", CoreMatchers.is(ErrorInfo.Severity.ERROR.toString())));
    }
}