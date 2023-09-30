package com.ellisonalves.thehotel.annotations;

import com.ellisonalves.thehotel.application.usecases.ManageGuestUseCase;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.usecases.room.ManageRoomUseCase;
import com.ellisonalves.thehotel.infrastructure.spring.config.JacksonConfig;
import com.ellisonalves.thehotel.infrastructure.spring.config.MessagesConfig;
import com.ellisonalves.thehotel.infrastructure.spring.rest.endpoints.BookingsController$CreateBookingsViewMapperImpl;
import com.ellisonalves.thehotel.infrastructure.spring.rest.mappers.RoomToDomainMapstructImpl;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.RoomAdapter;
import com.ellisonalves.thehotel.infrastructure.spring.rest.room.RoomToViewMapperImpl;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@IntegrationTest
@WebMvcTest
@Import({
        MessagesConfig.class,
        JacksonConfig.class,
        RoomToDomainMapstructImpl.class,
        RoomToViewMapperImpl.class,
        BookingsController$CreateBookingsViewMapperImpl.class,
        RoomAdapter.class,
        UseCaseMockConfig.class
})
public @interface ControllerTest {

}

@TestConfiguration
class UseCaseMockConfig {

    @Bean
    ManageRoomUseCase manageRoomUseCase() {
        return Mockito.mock(ManageRoomUseCase.class);
    }

    @Bean
    ManageGuestUseCase manageGuestUseCase() {
        return Mockito.mock(ManageGuestUseCase.class);
    }

    @Bean
    CreateBookingUseCase mockCreateBookingUseCase() {
        return Mockito.mock(CreateBookingUseCase.class);
    }

}
