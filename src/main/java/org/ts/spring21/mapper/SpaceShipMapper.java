package org.ts.spring21.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.ts.spring21.controller.dto.SpaceShipDTO;
import org.ts.spring21.model.SpaceShip;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class SpaceShipMapper {
    private static final SpaceShipMapper instance = Mappers.getMapper(SpaceShipMapper.class);

    public static SpaceShipMapper get() {
        return instance;
    }

    public abstract SpaceShip toSpaceShip(SpaceShipDTO dto);
}
