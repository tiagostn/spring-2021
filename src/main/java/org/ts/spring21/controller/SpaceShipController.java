package org.ts.spring21.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.service.SpaceShipService;

import java.util.List;

@RestController
@RequestMapping("spaceships")
@Log4j2
public class SpaceShipController {

    private SpaceShipService spaceShipService;

    public SpaceShipController(SpaceShipService spaceShipService) {
        this.spaceShipService = spaceShipService;
    }

    @GetMapping
    public List<SpaceShip> list() {
        log.info("List All");
        return spaceShipService.listAll();
    }

    @GetMapping("/{id}")
    public SpaceShip findById(@PathVariable("id") long id) {
        return spaceShipService.findById(id);
    }

    @PostMapping
    public ResponseEntity<SpaceShip> save(@RequestBody SpaceShip spaceShip) {
        return new ResponseEntity<>(spaceShipService.save(spaceShip), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        spaceShipService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping
    public ResponseEntity<SpaceShip> update(@RequestBody SpaceShip spaceShip) {
        return ResponseEntity.ok(spaceShipService.save(spaceShip));
    }



}
