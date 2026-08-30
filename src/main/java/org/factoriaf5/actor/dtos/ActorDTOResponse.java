package org.factoriaf5.actor.dtos;

import java.time.LocalDate;

public record ActorDTOResponse(Long id, String name, String nationality, LocalDate dateOfBirth) {
}
