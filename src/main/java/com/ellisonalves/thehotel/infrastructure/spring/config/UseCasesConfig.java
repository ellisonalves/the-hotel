package com.ellisonalves.thehotel.infrastructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ellisonalves.thehotel.application.usecases.ManageGuestUseCase;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingMapper;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.application.usecases.room.ManageRoomUseCase;
import com.ellisonalves.thehotel.application.usecases.room.RoomDomainMapper;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@Configuration
public class UseCasesConfig {

    @Bean
    ManageRoomUseCase manageRoomUseCase(RoomRepository repository, RoomDomainMapper mapper) {
        return new ManageRoomUseCase(repository, mapper);
    }

    @Bean
    ManageGuestUseCase manageGuestUseCase(GuestRepository repository) {
        return new ManageGuestUseCase(repository);
    }

    @Bean
    CreateBookingUseCase createBookingUseCase(
            BookingRepository repository,
            CreateBookingMapper mapper) {
        return new CreateBookingUseCase(repository, mapper);
    }

}
