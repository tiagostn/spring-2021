package org.ts.spring21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ts.spring21.model.SpaceShip;

public interface SpaceShipRepository extends JpaRepository<SpaceShip, Long> {
}
