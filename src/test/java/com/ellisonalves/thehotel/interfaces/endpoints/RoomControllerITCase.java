package com.ellisonalves.thehotel.interfaces.endpoints;

import com.ellisonalves.thehotel.domain.services.RoomService;
import com.ellisonalves.thehotel.domain.types.RoomType;
import com.ellisonalves.thehotel.interfaces.dtos.RoomDTO;
import com.ellisonalves.thehotel.interfaces.mapper.RoomMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

public class RoomControllerITCase extends AbstractIntegrationTestController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomMapper roomMapper;

    @Test
    public void shouldReadAllRooms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms[0].doorNumber", CoreMatchers.is(100)));
    }

    @Test
    public void shouldFindOneRoomForAGivenId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rooms/101"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorNumber", CoreMatchers.is(101)));
    }

    @Test
    public void shouldCreateANewRoom() throws Exception {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setDoorNumber(1000);
        roomDTO.setPricePerDay(new BigDecimal("35.40"));
        roomDTO.setType(RoomType.STANDARD);

        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/rooms");
        post
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(roomDTO));

        mockMvc.perform(post)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.doorNumber", CoreMatchers.notNullValue()));
    }

    @Test
    public void shouldUpdateAnExistingRoom() throws Exception {
        RoomDTO roomtDTO = roomMapper.toDTO(roomService.findOne(104));
        roomtDTO.setType(RoomType.PRESIDENT);
        roomtDTO.setPricePerDay(new BigDecimal("330.40"));

        MockHttpServletRequestBuilder put = MockMvcRequestBuilders.put("/rooms");
        put
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJson(roomtDTO));

        mockMvc.perform(put)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.equalTo("PRESIDENT")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerDay", CoreMatchers.is("330.40")));
    }
}