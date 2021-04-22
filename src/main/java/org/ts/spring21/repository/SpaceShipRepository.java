package org.ts.spring21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ts.spring21.model.SpaceShip;

import java.util.List;

public interface SpaceShipRepository extends JpaRepository<SpaceShip, Long> {
    List<SpaceShip> findByName(String name);
}
