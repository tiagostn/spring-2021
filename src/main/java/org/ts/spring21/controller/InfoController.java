package org.ts.spring21.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ts.spring21.model.SpaceShip;

import java.util.List;

@RestController
@RequestMapping("space")
public class InfoController {

    @GetMapping("ships")
    public List<SpaceShip> info() {
        List<SpaceShip> spaceShips = List.of(new SpaceShip("Saturn V"), new SpaceShip("Falcon IV"));
        System.out.println(spaceShips);
        return spaceShips;
    }
}
