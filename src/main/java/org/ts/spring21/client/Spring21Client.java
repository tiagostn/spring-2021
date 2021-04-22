package org.ts.spring21.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.ts.spring21.controller.dto.SpaceShipDTO;
import org.ts.spring21.model.SpaceShip;

import java.util.List;

@Log4j2
public class Spring21Client {

    public static void main(String[] args) {
        SpaceShipDTO dto = new RestTemplate().getForObject("http://localhost:8080/spaceships/5", SpaceShipDTO.class, 5L);
        log.info(dto);

        ResponseEntity<List<SpaceShip>> responseEntity = new RestTemplate().exchange("http://localhost:8080/spaceships/all", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        log.info(responseEntity.getBody());

    }
}
