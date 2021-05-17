package org.ts.spring21.controller.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class SpaceShipDTO {

    private Long id;

    @NotBlank(message = "SpaceShip's name cannot be empty")
    private String name;
    @URL(message = "Not a valid URL")
    @NotEmpty(message = "URL cannot be empty")
    private String url;

}
