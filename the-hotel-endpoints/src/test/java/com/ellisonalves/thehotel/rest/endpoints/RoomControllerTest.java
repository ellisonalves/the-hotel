package com.ellisonalves.thehotel.rest.endpoints;

import com.ellisonalves.thehotel.domain.services.RoomService;
import com.ellisonalves.thehotel.rest.UnitTestUtils;
import com.ellisonalves.thehotel.rest.application.config.JacksonConfig;
import com.ellisonalves.thehotel.rest.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.rest.application.exceptions.pojos.MessageSeverity;
import com.ellisonalves.thehotel.rest.interfaces.dtos.RoomDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    private ObjectMapper objectMapper = new JacksonConfig().objectMapper();

    @Before
    public void setup() {
        mockMvc = UnitTestUtils.createStandaloneSetup(roomController);
    }

    @Test
    public void findByIdInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/INVALID"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].severity", CoreMatchers.is(MessageSeverity.ERROR.toString())));
    }

    @Test
    public void findOneThrowsBadRequestWhenCustomerDoesNotExist() throws Exception {
        Mockito.when(roomService.findOne(Mockito.anyInt())).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/100"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].message", CoreMatchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0].severity", CoreMatchers.is(MessageSeverity.ERROR.toString())));
    }

    @Test
    public void shouldFailWhenTryingToCreateRoomWithIncorrectValues() throws Exception {
        RoomDTO roomDTO = new RoomDTO();

        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/rooms");
        post
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(roomDTO));

        mockMvc.perform(post)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}