package com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints;

import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingInput;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.vo.err.UseCaseResult;
import com.ellisonalves.thehotel.infrastructure.rest.api.BookingsApi;
import com.ellisonalves.thehotel.infrastructure.rest.model.BookingCreatedResponse;
import com.ellisonalves.thehotel.infrastructure.rest.model.CreateBookingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.OffsetDateTime;

@RestController
@RequestMapping(Routes.BASE_URL_V1)
public class BookingsController implements BookingsApi {

    private final CreateBookingUseCase useCase;
    private final CreateBookingsViewMapper mapper;

    public BookingsController(CreateBookingUseCase useCase, CreateBookingsViewMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<BookingCreatedResponse> createBooking(CreateBookingRequest request) {
        var result = useCase.execute(mapper.toDomain(request));

        return switch (result.resultType()) {
            case DATA_VALIDATION_ERROR -> ResponseEntity.badRequest().body(mapper.toProblemResponse(result));
            case ACCEPTED -> ResponseEntity.accepted().build();
        };

    }

    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
    public interface CreateBookingsViewMapper {

        @Mapping(target = "startDate", source = "from")
        @Mapping(target = "endDate", source = "until")
        CreateBookingInput toDomain(CreateBookingRequest view);

        @Mapping(target = "resourceId", ignore = true)
        @Mapping(target = "message", source = "content.message")
        BookingCreatedResponse toProblemResponse(UseCaseResult result);

        default Instant map(OffsetDateTime offsetDateTime) {
            if (offsetDateTime == null)
                return null;
            return offsetDateTime.toInstant();
        }

    }

}
