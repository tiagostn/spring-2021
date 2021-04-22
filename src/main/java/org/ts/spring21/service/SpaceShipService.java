package org.ts.spring21.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.repository.SpaceShipRepository;

import java.util.List;

@Service
public class SpaceShipService {

    private SpaceShipRepository repository;

    public SpaceShipService(SpaceShipRepository repository) {
        this.repository = repository;
    }

    public List<SpaceShip> listAll() {
        return repository.findAll();
    }

    public SpaceShip findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Space not found"));
    }

    public SpaceShip save(SpaceShip spaceShip) {
        return repository.save(spaceShip);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
