package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import com.ellisonalves.thehotel.annotations.ControllerTest;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest
public class BookingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreateBookingUseCase mockCreateBookingUseCase;

    private final UUID guestId = UUID.randomUUID();
    private final UUID roomId = UUID.randomUUID();
    private final Instant from = Instant.now();
    private final Instant until = from.plus(Duration.ofDays(5));

    @Test
    void shouldCreateBooking() throws Exception {
        when(mockCreateBookingUseCase.execute(any())).thenReturn(UseCaseResult.ok("e891677d-0ebf-475d-a967-dff6bb09d2ff", "Accepted!"));

        mockMvc.perform(validBookingPost())
                .andExpect(status().isAccepted())
                .andExpect(content().string(Matchers.emptyString()));
    }

    @Test
    void shouldNotCreateBookingWhenMandatoryFieldsAreNotProvided() throws Exception {
        mockMvc.perform(
                        post(Routes.Bookings.BOOKINGS)
                                .contentType(MediaType.APPLICATION_JSON).content("""
                                        {
                                            "guest_id": "",
                                            "room_id": "",
                                            "from": "",
                                            "until": ""
                                        }
                                        """))
                .andExpectAll(
                        status().isBadRequest(),
                        content().json("""
                                	{
                                		"errors":[
                                			{"field_name":"from","error_messages":["must not be null"]},
                                			{"field_name":"guestId","error_messages":["must not be null"]},
                                			{"field_name":"roomId","error_messages":["must not be null"]},
                                			{"field_name":"until","error_messages":["must not be null"]}]
                                	}
                                """));

    }

    @Test
    void shouldHandleUnprocessableEntityError() throws Exception {
        var response = new BookingCreatedResponse();

        response.setMessage("some error has happened");

        when(mockCreateBookingUseCase.execute(any())).thenReturn(UseCaseResult.dataValidationError("some error has happened"));

        mockMvc.perform(validBookingPost())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                        	"message": "some error has happened"
                        }
                        """, true));

    }

    private MockHttpServletRequestBuilder validBookingPost() {
        return post(Routes.Bookings.BOOKINGS).contentType(MediaType.APPLICATION_JSON).content(jsonRequest());
    }

    private String jsonRequest() {
        return """
                {
                    "guest_id": "%s",
                    "room_id": "%s",
                    "from": "%s",
                    "until": "%s"
                }
                """.formatted(guestId, roomId, from, until);
    }
}
