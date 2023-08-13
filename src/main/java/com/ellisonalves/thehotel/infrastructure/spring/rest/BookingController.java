package com.ellisonalves.thehotel.infrastructure.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.infrastructure.rest.BookingsApi;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;
import com.ellisonalves.thehotel.infrastructure.spring.rest.mappers.CreateBookingMapstruct;

@RestController
@RequestMapping("/api/v1")
public class BookingController implements BookingsApi {

    private final CreateBookingUseCase useCase;

    private final CreateBookingMapstruct mapper;

    public BookingController(CreateBookingUseCase useCase, CreateBookingMapstruct mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Void> createBooking(CreateBookingRequest request) {
        useCase.createBooking(mapper.toDomain(request));
        return ResponseEntity.accepted().build();
    }
}
