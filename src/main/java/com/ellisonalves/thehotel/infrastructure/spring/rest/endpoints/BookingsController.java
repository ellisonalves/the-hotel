package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.infrastructure.rest.api.BookingsApi;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;

@RestController
@RequestMapping(Routes.BASE_URL_V1)
public class BookingsController implements BookingsApi {

	private final BookingsAdapter adapter;

	public BookingsController(BookingsAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public ResponseEntity<BookingCreatedResponse> createBooking(CreateBookingRequest request) {
		var response = adapter.execute(request);

		switch (response.getLevel()) {
		case UNPROCESSABLE:
			return ResponseEntity.unprocessableEntity().body(response);
		case OK:
			return ResponseEntity.created(URI.create(Routes.Bookings.BOOKINGS.concat(response.getResourceId())))
					.body(response);
		default:
			throw new IllegalArgumentException("Something went wrong");
		}

	}

}
