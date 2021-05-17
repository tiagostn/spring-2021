package org.ts.spring21.service;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ts.spring21.controller.SpaceShipController;
import org.ts.spring21.controller.dto.SpaceShipDTO;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.repository.SpaceShipRepository;
import org.ts.spring21.util.SpaceShipCreator;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Log4j2
class SpaceShipServiceTest {
    @InjectMocks
    private SpaceShipService spaceShipService;
    @Mock
    private SpaceShipRepository spaceShipRepositoryMock;

    @BeforeEach
    void setUp() {
        List<SpaceShip> spaceShips = List.of(SpaceShipCreator.createValidSpaceShip());
        PageImpl<SpaceShip> spaceShipPage = new PageImpl<>(spaceShips);
        log.debug("Mock SpaceshipService");
        BDDMockito.when(spaceShipRepositoryMock.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(spaceShipPage);
        BDDMockito.when(spaceShipRepositoryMock.findAll()).thenReturn(spaceShips);
        BDDMockito.when(spaceShipRepositoryMock.findById(ArgumentMatchers.any())).thenReturn(Optional.of(SpaceShipCreator.createValidSpaceShip()));
        BDDMockito.when(spaceShipRepositoryMock.findByName(ArgumentMatchers.any())).thenReturn(spaceShips);
        BDDMockito.when(spaceShipRepositoryMock.save(ArgumentMatchers.any(SpaceShip.class))).thenReturn(SpaceShipCreator.createValidSpaceShip());
        BDDMockito.doNothing().when(spaceShipRepositoryMock).delete(ArgumentMatchers.any(SpaceShip.class));
    }

    @Test
    void listAll() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        Pageable pageable = PageRequest.of(1, 3);
        Page<SpaceShip> spaceShips = spaceShipService.listAll(pageable);
//        Assertions.assertThat(spaceShips).isNotNull();
        Assertions.assertThat(spaceShips.get()).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get().findFirst()).isNotEmpty();
        Assertions.assertThat(spaceShips.get().findFirst().get().getName()).isEqualTo(expectedName);
    }

    @Test
    void testListAll() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        List<SpaceShip> spaceShips = spaceShipService.listAll();
        Assertions.assertThat(spaceShips).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findById() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        List<SpaceShip> spaceShips = spaceShipService.listAll();
        Assertions.assertThat(spaceShips).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void findByName() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        List<SpaceShip> spaceShips = spaceShipService.findByName("teste");
        Assertions.assertThat(spaceShips).isNotEmpty().hasSize(1);
        Assertions.assertThat(spaceShips.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    void save() {
        String expectedName = SpaceShipCreator.createValidSpaceShip().getName();
        SpaceShip spaceShipDTOToBeSaved = SpaceShipCreator.createSpaceShipToBeSaved();
        SpaceShip saved = spaceShipService.save(spaceShipDTOToBeSaved);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getName()).isEqualTo(expectedName );
    }

    @Test
    void delete() {
        SpaceShip spaceShipDTOToBeDeleted = SpaceShipCreator.createValidSpaceShip();
        spaceShipService.delete(spaceShipDTOToBeDeleted.getId());
        Assertions.assertThat(spaceShipDTOToBeDeleted).isNotNull();
    }
}