package org.ts.spring21.repository;

import org.ts.spring21.model.SpaceShip;

import java.util.List;

public interface SpaceShipRepository {
    List<SpaceShip> listAll();
}
