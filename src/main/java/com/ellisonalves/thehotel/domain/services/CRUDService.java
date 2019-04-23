package com.ellisonalves.thehotel.domain.services;

public interface CRUDService<Entity, ID> {

    Entity save(Entity entity);

    Entity findOne(ID id);

    void delete(Entity entity);

}
