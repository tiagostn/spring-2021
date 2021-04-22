package org.ts.spring21.service;

import org.springframework.stereotype.Service;
import org.ts.spring21.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Space not found"));
    }

    public List<SpaceShip> findByName(String name) {
        return repository.findByName(name);
    }

    public SpaceShip save(SpaceShip spaceShip) {
        return repository.save(spaceShip);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}