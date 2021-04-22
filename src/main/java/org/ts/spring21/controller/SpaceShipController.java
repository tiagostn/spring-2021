package org.ts.spring21.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<SpaceShip> info() {
        log.info("List All");
        return spaceShipService.listAll();
    }
}
