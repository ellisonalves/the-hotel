package com.ellisonalves.thehotel.infrastructure.mappers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<Entity, DTO> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);

    default List<DTO> toDTOList(Collection<Entity> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

}
