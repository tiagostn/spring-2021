package org.ts.spring21.service;

import org.springframework.stereotype.Service;
import org.ts.spring21.model.SpaceShip;

import java.util.List;

@Service
public class SpaceShipService {
    public List<SpaceShip> listAll(){
        return List.of(new SpaceShip(1l,"Saturn V"), new SpaceShip(2l,"Falcon IV"));
    }
}
