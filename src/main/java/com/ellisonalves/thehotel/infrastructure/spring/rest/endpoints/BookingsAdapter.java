package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.springframework.stereotype.Component;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.vo.err.Result;
import com.ellisonalves.thehotel.application.vo.err.Result.ResultType;
import com.ellisonalves.thehotel.domain.entity.Booking;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse.LevelEnum;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;

@Component
public class BookingsAdapter {

	private final CreateBookingUseCase useCase;
	private final CreateBookingsViewMapper mapper;

	public BookingsAdapter(CreateBookingUseCase useCase, CreateBookingsViewMapper mapper) {
		this.useCase = useCase;
		this.mapper = mapper;
	}

	public BookingCreatedResponse execute(CreateBookingRequest request) {
		var result = useCase.createBooking(mapper.toDomain(request));

		switch (result.resultType()) {
		case OK:
			return mapper.toOkResponse(result);
		case UNPROCESSABLE_ENTITY:
			return mapper.toUnprocessableResponse(result);
		default:
			throw new IllegalArgumentException("The resultType can't be mapped -> %s".formatted(result.resultType()));
		}

	}

	@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
	public interface CreateBookingsViewMapper {

		@Mapping(target = "id", ignore = true)
		@Mapping(target = "guest.id", source = "guestId")
		@Mapping(target = "room.id", source = "roomId")
		@Mapping(target = "startDate", source = "from")
		@Mapping(target = "endDate", source = "until")
		@Mapping(target = "version", ignore = true)
		@Mapping(target = "createdAt", ignore = true)
		Booking toDomain(CreateBookingRequest view);

		@Mapping(target = "resourceId", ignore = true)
		@Mapping(target = "message", source = "content.message")
		@Mapping(target = "level", source = "resultType")
		BookingCreatedResponse toUnprocessableResponse(Result result);

		@Mapping(target = "resourceId", source = "content.resourceId")
		@Mapping(target = "message", source = "content.message")
		@Mapping(target = "level", source = "resultType")
		BookingCreatedResponse toOkResponse(Result result);

		@ValueMapping(target = "OK", source = "OK")
		@ValueMapping(target = "UNPROCESSABLE", source = "UNPROCESSABLE_ENTITY")
		LevelEnum mapLevel(ResultType resultType);

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
