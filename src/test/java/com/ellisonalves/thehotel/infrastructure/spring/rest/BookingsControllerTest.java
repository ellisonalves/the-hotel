package com.ellisonalves.thehotel.infrastructure.spring.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ellisonalves.thehotel.annotations.ControllerTest;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints.BookingsAdapter;

@ControllerTest
public class BookingsControllerTest {

	private static final String API_V1_BOOKINGS = "/api/v1/bookings";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingsAdapter mockBookingsAdapter;

	private UUID guestId = UUID.randomUUID();
	private UUID roomId = UUID.randomUUID();
	private Instant from = Instant.now();
	private Instant until = from.plus(Duration.ofDays(5));

	@Test
	void shouldCreateBooking() throws Exception {
		Mockito.when(mockBookingsAdapter.adapt(Mockito.any())).thenReturn(new BookingCreatedResponse().statusCode(201));

		mockMvc.perform(post(API_V1_BOOKINGS).contentType(MediaType.APPLICATION_JSON).content("""
				{
				    "guest_id": "%s",
				    "room_id": "%s",
				    "from": "%s",
				    "until": "%s"
				}
				""".formatted(guestId, roomId, from, until))).andExpect(status().isCreated());
	}

	@Test
	void shouldNotCreateBookingWhenMandatoryFieldsAreNotProvided() throws Exception {
		mockMvc.perform(post(API_V1_BOOKINGS).contentType(MediaType.APPLICATION_JSON).content("""
				{
				    "guest_id": "",
				    "room_id": "",
				    "from": "",
				    "until": ""
				}
				""")).andExpect(status().isBadRequest());
	}
}
