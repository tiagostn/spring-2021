package org.ts.spring21.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ts.spring21.controller.dto.SpaceShipDTO;
import org.ts.spring21.controller.response.ApiResponse;
import org.ts.spring21.mapper.SpaceShipMapper;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.service.SpaceShipService;

import javax.validation.Valid;
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
    public ApiResponse list() {
        return ApiResponse.builder().data(spaceShipService.listAll()).build();
    }

    @GetMapping("/{id}")
    public SpaceShip findById(@PathVariable("id") long id) {
        return spaceShipService.findById(id);
    }

    @GetMapping("/find")
    public List<SpaceShip> findByName(@RequestParam String name) {
        return spaceShipService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<SpaceShip> save(@RequestBody @Valid SpaceShipDTO spaceShip) {
        return new ResponseEntity<>(spaceShipService.save(SpaceShipMapper.get().toSpaceShip(spaceShip)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        spaceShipService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<SpaceShip> update(@RequestBody SpaceShipDTO spaceShip) {
        return ResponseEntity.ok(spaceShipService.save(SpaceShipMapper.get().toSpaceShip(spaceShip)));
    }
}
