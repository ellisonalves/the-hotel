package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.ellisonalves.thehotel.annotations.ControllerTest;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse.LevelEnum;
import com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints.adapter.BookingsAdapter;

@ControllerTest
public class BookingsControllerTest {

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
		var createdBookingId = "e891677d-0ebf-475d-a967-dff6bb09d2ff";
		var response = new BookingCreatedResponse();

		response.setResourceId(createdBookingId);
		response.setMessage("Created!");
		response.setLevel(LevelEnum.CREATED);

		when(mockBookingsAdapter.execute(any())).thenReturn(response);

		mockMvc.perform(validBookingPost()).andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, Routes.Bookings.BOOKINGS.concat(createdBookingId)))
				.andExpect(content().json("""
							{
								"resource_id": "e891677d-0ebf-475d-a967-dff6bb09d2ff",
								"message": "Created!",
								"level": "CREATED"
							}
						""", true));
	}

	@Test
	void shouldNotCreateBookingWhenMandatoryFieldsAreNotProvided() throws Exception {
		mockMvc.perform(post(Routes.Bookings.BOOKINGS).contentType(MediaType.APPLICATION_JSON).content("""
				{
				    "guest_id": "",
				    "room_id": "",
				    "from": "",
				    "until": ""
				}
				""")).andExpectAll(status().isBadRequest(), content().json("""
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
		response.setLevel(LevelEnum.UNPROCESSABLE_ENTITY);
		response.setMessage("some error has happened");

		when(mockBookingsAdapter.execute(any())).thenReturn(response);

		mockMvc.perform(validBookingPost()).andExpect(status().isUnprocessableEntity()).andExpect(content().json("""
				{
					"level": "UNPROCESSABLE_ENTITY",
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
