package org.ts.spring21.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SpaceShipDTO {

    private Long id;

    @NotBlank(message = "SpaceShip's name cannot be empty")
    private String name;

}
