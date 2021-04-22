package org.ts.spring21.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
public class SpaceShipDTO {

    private Long id;

    @NotBlank(message = "SpaceShip's name cannot be empty")
    private String name;
    @URL(message = "Not a valid URL")
    private String url;

}
