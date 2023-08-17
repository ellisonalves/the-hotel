package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.vo.err.Result.Content;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;

@Component
public class BookingsAdapter {

	private final CreateBookingUseCase useCase;
	private final CreateBookingsViewMapper mapper;

	public BookingsAdapter(CreateBookingUseCase useCase, CreateBookingsViewMapper mapper) {
		this.useCase = useCase;
		this.mapper = mapper;
	}

	public BookingCreatedResponse adapt(CreateBookingRequest request) {
		var result = useCase.createBooking(mapper.toDomain(request));
		return mapper.toCreatedBookingResponse(result.content());
	}

	@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
	public interface CreateBookingsViewMapper {

		@Mapping(target = "id", ignore = true)
		@Mapping(target = "guest.id", source = "guestId")
		@Mapping(target = "room.id", source = "roomId")
		@Mapping(target = "startDate", source = "from")
		@Mapping(target = "endDate", source = "until")
		@Mapping(target = "version", ignore = true)
		Booking toDomain(CreateBookingRequest view);

		BookingCreatedResponse toCreatedBookingResponse(Content content);

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
