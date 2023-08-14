package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.infrastructure.rest.BookingsApi;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;
import com.ellisonalves.thehotel.infrastructure.spring.rest.adapters.CreateBookingAdapter;

@RestController
@RequestMapping("/api/v1")
public class BookingsController implements BookingsApi {

    private final CreateBookingAdapter adapter;

    public BookingsController(CreateBookingAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public ResponseEntity<Void> createBooking(CreateBookingRequest request) {
        adapter.createBooking(request);
        return ResponseEntity.accepted().build();
    }

}
