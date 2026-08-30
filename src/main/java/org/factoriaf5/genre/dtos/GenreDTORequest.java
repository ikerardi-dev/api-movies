package org.factoriaf5.genre.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenreDTORequest(

        @NotBlank(message = "The genre name is required")
        @Size(max = 60, message = "The genre name cannot be longer than 60 characters")
        String name) {
}
