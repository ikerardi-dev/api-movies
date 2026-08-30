package org.factoriaf5.movie.dtos;

import org.factoriaf5.actor.dtos.ActorDTOResponse;
import org.factoriaf5.genre.dtos.GenreDTOResponse;

import java.util.List;

public record MovieDTOResponse(
        Long id,
        String title,
        String synopsis,
        Integer durationMinutes,
        String director,
        GenreDTOResponse genre,
        Integer year,
        List<ActorDTOResponse> actors) {
}
