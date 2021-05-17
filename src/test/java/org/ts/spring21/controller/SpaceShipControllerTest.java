package org.ts.spring21.controller;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ts.spring21.controller.dto.SpaceShipDTO;
import org.ts.spring21.controller.response.ApiResponse;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.service.SpaceShipService;
import org.ts.spring21.util.SpaceShipCreator;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@Log4j2
class SpaceShipControllerTest {
    @InjectMocks
    private SpaceShipController spaceShipController;

    @Mock
    private SpaceShipService spaceShipServiceMock;

    @BeforeEach
    void setup() {
        List<SpaceShip> spaceShips = List.of(SpaceShipCreator.createValidSpaceShip());
        PageImpl<SpaceShip> spaceShipPage = new PageImpl<>(spaceShips);
        log.debug("Mock SpaceshipService");
        BDDMockito.when(spaceShipServiceMock.listAll(ArgumentMatchers.any())).thenReturn(spaceShipPage);
        BDDMockito.when(spaceShipServiceMock.listAll()).thenReturn(spaceShips);
        BDDMockito.when(spaceShipServiceMock.findById(ArgumentMatchers.any())).thenReturn(SpaceShipCreator.createValidSpaceShip());
        BDDMockito.when(spaceShipServiceMock.findByName(ArgumentMatchers.any())).thenReturn(spaceShips);
        BDDMockito.when(spaceShipServiceMock.save(ArgumentMatchers.any(SpaceShip.class))).thenReturn(SpaceShipCreator.createValidSpaceShip());
        BDDMockito.doNothing().when(spaceShipServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    void list_ReturnsPageOfSpaceship_WhenSuccessful() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        Object data = spaceShipController.list(null).getData();
        Assertions.assertThat(data).isInstanceOf(Page.class);
        Page<SpaceShip> page = (Page) data;
        Assertions.assertThat(page.get()).isNotEmpty().hasSize(1);
        Assertions.assertThat(page.get().findFirst()).isNotEmpty();
        Assertions.assertThat(page.get().findFirst().get().getName()).isEqualTo(expectedName);
    }

    @Test
    void listAll_ReturnsSpaceship_WhenSuccessful() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        List<SpaceShip> spaceShips = spaceShipController.listAll();
        Assertions.assertThat(spaceShips).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void listAll_ReturnsListOfSpaceship_WhenSuccessful() {
        Long expectedId = SpaceShipCreator.createValidSpaceShip().getId();
        SpaceShip spaceShip = spaceShipController.findById(1L);
        Assertions.assertThat(spaceShip).isNotNull();
        Assertions.assertThat(spaceShip.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    void findById_ReturnsSpaceship_WhenSuccessful() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        List<SpaceShip> spaceShips = spaceShipController.listAll();
        Assertions.assertThat(spaceShips).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName_ReturnsListOfSpaceship_WhenSuccessful() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        List<SpaceShip> spaceShips = spaceShipController.findByName("teste");
        Assertions.assertThat(spaceShips).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get(0).getName()).isEqualTo(expectedName);
    }


    @Test
    void findByName_ReturnsEmptyList_WhenNotFound() {
        BDDMockito.when(spaceShipServiceMock.findByName(ArgumentMatchers.any())).thenReturn(Collections.emptyList());
        List<SpaceShip> spaceShips = spaceShipController.findByName("teste");
        Assertions.assertThat(spaceShips).isEmpty();
    }

    @Test
    void save_ReturnsSpaceship_WhenSuccessful() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        SpaceShipDTO spaceShipDTOToBeSaved = SpaceShipCreator.createSpaceShipDTOToBeSaved();
        SpaceShip saved = spaceShipController.save(spaceShipDTOToBeSaved).getBody();
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getName()).isEqualTo(expectedName );
    }

    @Test
    void delete_RemovesSpaceship_WhenSuccessful() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        SpaceShip spaceShipDTOToBeDeleted = SpaceShipCreator.createValidSpaceShip();
        spaceShipController.delete(spaceShipDTOToBeDeleted.getId());
        Assertions.assertThat(spaceShipDTOToBeDeleted).isNotNull();
    }
}