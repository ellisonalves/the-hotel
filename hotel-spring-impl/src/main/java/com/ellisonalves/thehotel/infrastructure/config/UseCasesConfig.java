package com.ellisonalves.thehotel.infrastructure.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ellisonalves.thehotel.application.usecases.ManageRoomUseCase;
import com.ellisonalves.thehotel.domain.entity.Room;
import com.ellisonalves.thehotel.domain.repository.RoomRepository;

@Configuration
public class UseCasesConfig {

    @Bean
    public ManageRoomUseCase manageRoomUseCase(RoomRepository<Room, UUID> repository) {
        return new ManageRoomUseCase(repository);
    }

}
