package org.factoriaf5.year.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record YearDTORequest(

        @NotNull(message = "The year value is required")
        @Min(value = 1888, message = "The year must be later than 1888 (birth of cinema)")
        @Max(value = 2100, message = "The year cannot be later than 2100")
        Integer value) {
}
