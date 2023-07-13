package com.ellisonalves.thehotel.infrastructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ellisonalves.thehotel.application.usecases.ManageGuestUseCase;
import com.ellisonalves.thehotel.application.usecases.ManageRoomUseCase;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingMapper;
import com.ellisonalves.thehotel.application.usecases.booking.CreateBookingUseCase;
import com.ellisonalves.thehotel.domain.repository.BookingRepository;
import com.ellisonalves.thehotel.domain.repository.GuestRepository;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@Configuration
public class UseCasesConfig {

    @Bean
    public ManageRoomUseCase manageRoomUseCase(RoomRepository repository) {
        return new ManageRoomUseCase(repository);
    }

    @Bean
    public ManageGuestUseCase manageGuestUseCase(GuestRepository repository) {
        return new ManageGuestUseCase(repository);
    }

    @Bean
    public CreateBookingUseCase createBookingUseCase(
            BookingRepository repository,
            CreateBookingMapper mapper) {
        return new CreateBookingUseCase(repository, mapper);
    }

}
