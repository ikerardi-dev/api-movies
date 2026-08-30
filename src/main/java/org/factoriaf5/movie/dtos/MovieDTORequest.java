package org.factoriaf5.movie.dtos;

import jakarta.validation.constraints.*;

import java.util.List;

/**
 * Input DTO to create/update a movie. It only references the genre/year ids
 * and the actor ids (not the full objects), avoiding exposing the
 * persistence model directly.
 */
public record MovieDTORequest(

        @NotBlank(message = "The title is required")
        @Size(max = 150, message = "The title cannot be longer than 150 characters")
        String title,

        @Size(max = 2000, message = "The synopsis cannot be longer than 2000 characters")
        String synopsis,

        @Positive(message = "The duration must be a positive number of minutes")
        Integer durationMinutes,

        @Size(max = 100, message = "The director's name cannot be longer than 100 characters")
        String director,

        @NotNull(message = "The genre id is required")
        Long genreId,

        @NotNull(message = "The year id is required")
        Long yearId,

        List<Long> actorIds) {
}
