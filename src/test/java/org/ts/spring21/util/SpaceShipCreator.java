package org.ts.spring21.util;

import org.ts.spring21.controller.dto.SpaceShipDTO;
import org.ts.spring21.mapper.SpaceShipMapper;
import org.ts.spring21.model.SpaceShip;

public class SpaceShipCreator {
    public static SpaceShip createSpaceShipToBeSaved() {
        return SpaceShip.builder().name("Space Test").build();
    }

    public static SpaceShip createValidSpaceShip() {
        return SpaceShip.builder().name("Space Test").id(1L).build();
    }

    public static SpaceShip createValidUpdatedSpaceShip() {
        return SpaceShip.builder().name("Space Test 2").id(1L).build();
    }

    public static SpaceShipDTO createSpaceShipDTOToBeSaved() {
        return SpaceShipDTO.builder().id(1L).name("Ship Test 1").build();
    }
}
