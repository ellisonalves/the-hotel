package com.ellisonalves.thehotel.infrastructure.spring.rest.room;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.spring.config.MessagesConfig;
import com.ellisonalves.thehotel.infrastructure.spring.jpa.entity.RoomJpa;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.RoomAdapter;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.model.RoomCreateDto;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.model.RoomList;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest({ RoomController.class })
@Import({ MessagesConfig.class })
class RoomControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private RoomAdapter mockAdapter;

        @Test
        void shouldPostSuccessfully() throws Exception {
                var request = new RoomCreateDto();
                request.setDoorNumber("123");
                request.setPricePerDay(BigDecimal.TEN);
                request.setRoomType(RoomType.STANDARD);

                MockHttpServletRequestBuilder post = post("/rooms");
                post
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString((request)));

                mockMvc.perform(post)
                                .andExpect(status().isCreated());
        }

        @Test
        void shouldPutSuccessfully() throws Exception {
                var roomId = UUID.randomUUID();
                var request = new RoomCreateDto();
                request.setDoorNumber("123");
                request.setPricePerDay(BigDecimal.TEN);
                request.setRoomType(RoomType.STANDARD);

                Room persisted = new RoomJpa();
                persisted.setDoorNumber(request.getDoorNumber());
                persisted.setPricePerDay(request.getPricePerDay());
                persisted.setRoomType(RoomType.STANDARD);

                MockHttpServletRequestBuilder put = put("/rooms/" + roomId);
                put
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString((request)));

                mockMvc.perform(put)
                                .andExpect(status().isNoContent());

                verify(mockAdapter, only()).updateRoom(any(), any());
        }

        @Test
        void shouldFailWhenTryingToCreateRoomWithInvalidValues() throws Exception {
                RoomCreateDto roomDTO = new RoomCreateDto();

                MockHttpServletRequestBuilder post = post("/rooms");
                post
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString((roomDTO)));

                mockMvc.perform(post)
                                .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenRoomIsNotFound() throws Exception {
                var doorNumber = "NOT_FOUND";
                when(mockAdapter.findByDoorNumber(doorNumber)).thenThrow(new ResourceNotFoundException());

                mockMvc.perform(get("/rooms/" + doorNumber))
                                .andExpectAll(
                                                status().isNotFound(),

                                                jsonPath("$.errors", Matchers.hasSize(1)),

                                                jsonPath("$.errors[0].message", notNullValue()));
        }

    @Test
    void shouldReturnAnEmptyListOfRooms() throws Exception {
        when(mockAdapter.findAll()).thenReturn(new RoomList(Collections.emptyList()));

        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(
                        content().json("""
                                {"rooms" : []}
                                """));

    }

}