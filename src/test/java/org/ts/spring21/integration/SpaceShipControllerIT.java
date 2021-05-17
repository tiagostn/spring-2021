package org.ts.spring21.integration;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.ts.spring21.controller.response.ApiResponse;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.repository.SpaceShipRepository;
import org.ts.spring21.service.SpaceShipService;
import org.ts.spring21.util.SpaceShipCreator;

import java.util.LinkedHashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@Log4j2
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SpaceShipControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private SpaceShipRepository spaceShipRepository;
    @Autowired
    private SpaceShipService spaceShipService;

    @Test
    void list_ReturnsPageOfSpaceship_WhenSuccessful() {
        SpaceShip spaceShipSaved = spaceShipService.save(SpaceShipCreator.createSpaceShipToBeSaved());

        ResponseEntity<ApiResponse> responseEntity = testRestTemplate.exchange("/spaceships", HttpMethod.GET, null, ApiResponse.class);
        log.info(responseEntity.getBody().toString());
        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getData()).isNotNull();
        Object data = responseEntity.getBody().getData();
//        Assertions.assertThat(data).isInstanceOf(Page.class);
        LinkedHashMap<String, Object> pageable = (LinkedHashMap) data;
        Assertions.assertThat(pageable.get("empty")).isEqualTo(Boolean.TRUE);

    }
}
