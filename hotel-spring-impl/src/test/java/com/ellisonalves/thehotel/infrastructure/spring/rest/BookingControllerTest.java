package com.ellisonalves.thehotel.infrastructure.spring.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.infrastructure.spring.config.JacksonConfig;
import com.ellisonalves.thehotel.infrastructure.spring.config.MessagesConfig;

@WebMvcTest({ BookingController.class, MessagesConfig.class, JacksonConfig.class })
public class BookingControllerTest {

    private static final String API_V1_BOOKINGS = "/api/v1/bookings";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateBookingUseCase useCase;

    private UUID guestId = UUID.randomUUID();
    private UUID roomId = UUID.randomUUID();
    private Instant from = Instant.now();
    private Instant until = from.plus(Duration.ofDays(5));

    @Test
    void shouldCreateBooking() throws Exception {
        mockMvc.perform(
                post(API_V1_BOOKINGS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                            "guest_id": "%s",
                                            "room_id": "%s",
                                            "from": "%s",
                                            "until": "%s"
                                        }
                                        """.formatted(guestId, roomId, from, until)))
                .andExpect(
                        status().isAccepted());
    }

    @Test
    void shouldNotCreateBookingWhenMandatoryFieldsAreNotProvided() throws Exception {
        mockMvc.perform(
                post(API_V1_BOOKINGS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                            "guest_id": "",
                                            "room_id": "",
                                            "from": "",
                                            "until": ""
                                        }
                                        """))
                .andExpect(
                        status().isBadRequest());
    }
}
