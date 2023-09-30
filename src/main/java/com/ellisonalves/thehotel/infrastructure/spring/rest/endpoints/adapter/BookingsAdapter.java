package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints.adapter;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingInput;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult.ResultType;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse.LevelEnum;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
public class BookingsAdapter {

    private final CreateBookingUseCase useCase;
    private final CreateBookingsViewMapper mapper;

    public BookingsAdapter(CreateBookingUseCase useCase, CreateBookingsViewMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    public BookingCreatedResponse execute(CreateBookingRequest request) {
        var result = useCase.execute(mapper.toDomain(request));

        return switch (result.resultType()) {
            case OK -> mapper.toOkResponse(result);
            case UNPROCESSABLE -> mapper.toUnprocessableResponse(result);
        };
    }

    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface CreateBookingsViewMapper {

        @Mapping(target = "startDate", source = "from")
        @Mapping(target = "endDate", source = "until")
        CreateBookingInput toDomain(CreateBookingRequest view);

        @Mapping(target = "resourceId", ignore = true)
        @Mapping(target = "message", source = "content.message")
        @Mapping(target = "level", source = "resultType")
        BookingCreatedResponse toUnprocessableResponse(UseCaseResult result);

        @Mapping(target = "resourceId", source = "content.resourceId")
        @Mapping(target = "message", source = "content.message")
        @Mapping(target = "level", source = "resultType")
        BookingCreatedResponse toOkResponse(UseCaseResult result);

        @ValueMapping(target = "CREATED", source = "OK")
        @ValueMapping(target = "UNPROCESSABLE_ENTITY", source = "UNPROCESSABLE")
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
