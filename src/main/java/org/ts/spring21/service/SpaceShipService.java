package org.ts.spring21.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.ts.spring21.model.SpaceShip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SpaceShipService {


    private static final List<SpaceShip> spaceShips;

    static {
        spaceShips = new ArrayList<>(List.of(new SpaceShip(1L, "Saturn V"), new SpaceShip(2L, "Falcon IV")));
    }

    public List<SpaceShip> listAll() {
        return spaceShips;
    }

    public SpaceShip findById(Long id) {
        return spaceShips.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Space not found"));
    }

    public SpaceShip save(SpaceShip spaceShip) {
        if (spaceShip.getId() == null) {
            spaceShip.setId(ThreadLocalRandom.current().nextLong(3, 9999));
            spaceShips.add(spaceShip);
        } else {
            spaceShips.remove(findById(spaceShip.getId()));
            spaceShips.add(spaceShip);
        }
        return spaceShip;
    }

    public void delete(long id) {
        SpaceShip spaceShip = findById(id);
        spaceShips.remove(spaceShip);
    }
}
