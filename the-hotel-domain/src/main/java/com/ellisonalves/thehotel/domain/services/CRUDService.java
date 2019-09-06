package com.ellisonalves.thehotel.domain.services;

import java.util.Optional;

public interface CRUDService<Entity, ID> {

    Entity save(Entity entity);

    Optional<Entity> findOne(ID id);

    void delete(Entity entity);

}
