package com.ellisonalves.thehotel.infrastructure.controller.room;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.ellisonalves.thehotel.application.exceptions.ResourceNotFoundException;
import com.ellisonalves.thehotel.application.usecases.ManageRoomUseCase;
import com.ellisonalves.thehotel.domain.aggregates.RoomType;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.infrastructure.config.MessagesConfig;
import com.ellisonalves.thehotel.infrastructure.jpa.entity.RoomJpa;
import com.ellisonalves.thehotel.infrastructure.mappers.RoomMapperImpl;
import com.ellisonalves.thehotel.pojos.MessageSeverity;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest({ RoomController.class })
@Import({ RoomMapperImpl.class, MessagesConfig.class })
public class RoomControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        private ManageRoomUseCase mockUseCase;

        @Test
        public void shouldPostSuccessfuly() throws Exception {
                var request = new RoomRequest();
                request.setDoorNumber("123");
                request.setPricePerDay(BigDecimal.TEN);
                request.setType(RoomType.STANDARD);

                MockHttpServletRequestBuilder post = post("/rooms");
                post
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString((request)));

                mockMvc.perform(post)
                                .andExpect(status().isCreated());
        }

        @Test
        public void shouldPutSuccessfuly() throws Exception {
                var roomId = UUID.randomUUID();
                var request = new RoomRequest();
                request.setDoorNumber("123");
                request.setPricePerDay(BigDecimal.TEN);
                request.setType(RoomType.STANDARD);

                Room persisted = new RoomJpa();
                persisted.setDoorNumber(request.getDoorNumber());
                persisted.setPricePerDay(request.getPricePerDay());
                persisted.setRoomType(RoomType.STANDARD);

                when(mockUseCase.findById(roomId)).thenReturn(persisted);

                MockHttpServletRequestBuilder put = put("/rooms/" + roomId);
                put
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString((request)));

                mockMvc.perform(put)
                                .andExpect(status().isNoContent());
        }

        @Test
        public void shouldFailWhenTryingToCreateRoomWithInvalidValues() throws Exception {
                RoomRequest roomDTO = new RoomRequest();

                MockHttpServletRequestBuilder post = post("/rooms");
                post
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString((roomDTO)));

                mockMvc.perform(post)
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void shouldReturnBadRequestWhenRoomIsNotFound() throws Exception {
                var doorNumber = "NOT_FOUND";
                when(mockUseCase.findByDoorNumber(doorNumber)).thenThrow(new ResourceNotFoundException());

                mockMvc.perform(get("/rooms/" + doorNumber))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.errors[0].message",
                                                notNullValue()))
                                .andExpect(jsonPath("$.errors[0].severity",
                                                is(MessageSeverity.ERROR.toString())));
        }

        @Test
        public void shouldReturnAnEmptyListOfRooms() throws Exception {
                mockMvc.perform(get("/rooms/"))
                                .andExpect(status().isOk())
                                .andExpect(
                                                content().json("""
                                                                {"rooms" : []}
                                                                """));

        }

}