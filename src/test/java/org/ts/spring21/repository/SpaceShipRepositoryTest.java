package org.ts.spring21.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ts.spring21.model.SpaceShip;
import org.ts.spring21.util.SpaceShipCreator;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

//import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("Spaceship repository test")
class SpaceShipRepositoryTest {

    @Autowired
    private SpaceShipRepository spaceShipRepository;

    @Test
    @DisplayName("Save persists spaceship when successful")
    void save_peristSpaceShip_WhenSuccessful() {
        SpaceShip spaceShip = SpaceShipCreator.createSpaceShipToBeSaved();
        SpaceShip saved = spaceShipRepository.save(spaceShip);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getName()).isEqualTo(spaceShip.getName());
    }

    @Test
    @DisplayName("Save updates spaceship when successful")
    void save_updateSpaceShip_WhenSuccessful() {
        SpaceShip spaceShip = SpaceShipCreator.createSpaceShipToBeSaved();
        SpaceShip saved = spaceShipRepository.save(spaceShip);
        Assertions.assertThat(spaceShip.getName()).isEqualTo(saved.getName());
        saved.setName("Other");
        SpaceShip updated = spaceShipRepository.save(saved);
        Assertions.assertThat(updated).isNotNull();
        Assertions.assertThat(updated.getId()).isNotNull();
        Assertions.assertThat(updated.getName()).isEqualTo(saved.getName());
    }

    @Test
    @DisplayName("Delete removes spaceship when successful")
    void delete_RemovesSpaceShip_WhenSuccessful() {
        SpaceShip spaceShip = SpaceShipCreator.createSpaceShipToBeSaved();
        SpaceShip saved = spaceShipRepository.save(spaceShip);
        saved.setName("Other");
        spaceShipRepository.delete(saved);
        Optional<SpaceShip> deleted = spaceShipRepository.findById(saved.getId());
        Assertions.assertThat(deleted).isEmpty();
    }

    @Test
    @DisplayName("FindByName returns spaceship when successful")
    void findByName_ReturnsSpaceShip_WhenSuccessful() {
        SpaceShip spaceShip = SpaceShipCreator.createSpaceShipToBeSaved();
        SpaceShip saved = spaceShipRepository.save(spaceShip);
        List<SpaceShip> spaceShips = spaceShipRepository.findByName(saved.getName());
        Assertions.assertThat(spaceShips).isNotEmpty().contains(saved);
    }

    @Test
    @DisplayName("FindByName returns spaceship when successful")
    void findByName_ReturnsEmptyList_WhenNotFound() {
        List<SpaceShip> spaceShips = spaceShipRepository.findByName("99999");
        Assertions.assertThat(spaceShips).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when spaceship name is empty")
    void save_ThrowConstraintViolationExcpetion_WhenNameIsEmpty() {
        SpaceShip spaceShip = new SpaceShip();
        // Assertions.assertThatThrownBy(() -> spaceShipRepository.save(spaceShip)).isInstanceOf(ConstraintViolationException.class);
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> spaceShipRepository.save(spaceShip))
                .withMessageContaining("Name of Spaceship must not be empty");
    }

}