package org.ts.spring21.integration;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
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
class SpaceShipControllerIT {
    @Autowired
    @Qualifier("testRestTemplateRoleUser")
    private TestRestTemplate testRestTemplateRoleUser;
//    @LocalServerPort
//    private int port;
    @Autowired
    private SpaceShipRepository spaceShipRepository;
    @Autowired
    private SpaceShipService spaceShipService;

    @TestConfiguration
    @Lazy
    static class Config {
        @Bean(name = "testRestTemplateRoleUser")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder builder = new RestTemplateBuilder()
                    .basicAuthentication("user", "1234")
                    .rootUri("http://localhost:" +port);
            return new TestRestTemplate(builder);
        }
    }

    @Test
    void list_ReturnsPageOfSpaceship_WhenSuccessful() {
        SpaceShip spaceShipSaved = spaceShipService.save(SpaceShipCreator.createSpaceShipToBeSaved());

        ResponseEntity<ApiResponse<SpaceShip>> responseEntity = testRestTemplateRoleUser.exchange("/spaceships", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
//        log.info(responseEntity.getBody().toString());
        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getBody()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getData()).isNotNull();
        Object data = responseEntity.getBody().getData();
//        Assertions.assertThat(data).isInstanceOf(Page.class);
//        LinkedHashMap<String, Object> pageable = (LinkedHashMap) data;
//        Assertions.assertThat(pageable.get("empty")).isEqualTo(Boolean.TRUE);

    }
}
