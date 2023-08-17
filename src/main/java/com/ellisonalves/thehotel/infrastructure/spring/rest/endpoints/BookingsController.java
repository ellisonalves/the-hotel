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
		var result = adapter.adapt(request);

		if (result == null || result.getStatusCode() == null) {
			return ResponseEntity.unprocessableEntity().build();
		}

		if (result.getStatusCode() > 399 && result.getStatusCode() < 499) {
			return ResponseEntity.badRequest().body(result);
		}

		return ResponseEntity.created(URI.create(Routes.Bookings.BOOKINGS.concat(result.getId()))).build();
	}

}
