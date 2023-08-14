package com.ellisonalves.thehotel.infrastructure.spring.rest.adapters;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBooking;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;

@Component
public class CreateBookingAdapter {

    private final CreateBookingUseCase useCase;
    private final CreateBookingsViewMapper mapper;

    public CreateBookingAdapter(CreateBookingUseCase useCase, CreateBookingsViewMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    public void createBooking(CreateBookingRequest request) {
        useCase.createBooking(mapper.toDomain(request));
    }

    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    interface CreateBookingsViewMapper {

        CreateBooking toDomain(CreateBookingRequest view);

        default Instant map(OffsetDateTime offsetDateTime) {
            if (offsetDateTime == null)
                return null;
            return offsetDateTime.toInstant();
        }

        default OffsetDateTime map(Instant instant) {
            if (instant == null)
                return null;
            return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }

}
