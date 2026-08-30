package org.factoriaf5.actor.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ActorDTORequest(

        @NotBlank(message = "The actor's name is required")
        @Size(max = 100, message = "The name cannot be longer than 100 characters")
        String name,

        @Size(max = 60, message = "The nationality cannot be longer than 60 characters")
        String nationality,

        @PastOrPresent(message = "The date of birth cannot be in the future")
        LocalDate dateOfBirth) {
}
