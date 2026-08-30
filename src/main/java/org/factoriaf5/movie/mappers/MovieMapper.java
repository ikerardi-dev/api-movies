package org.factoriaf5.movie.mappers;

import org.factoriaf5.actor.mappers.ActorMapper;
import org.factoriaf5.genre.mappers.GenreMapper;
import org.factoriaf5.movie.MovieEntity;
import org.factoriaf5.movie.dtos.MovieDTOResponse;

import java.util.Comparator;
import java.util.List;

// Static mapper, no Spring bean (same as the CountryMapper shown in class).
public class MovieMapper {

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        if (entity == null) return null;

        List<org.factoriaf5.actor.dtos.ActorDTOResponse> actors = entity.getActors().stream()
                .map(ActorMapper::toDTO)
                .sorted(Comparator.comparing(a -> a.name() == null ? "" : a.name()))
                .toList();

        return new MovieDTOResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getSynopsis(),
                entity.getDurationMinutes(),
                entity.getDirector(),
                GenreMapper.toDTO(entity.getGenre()),
                entity.getYear() != null ? entity.getYear().getValue() : null,
                actors);
    }

    public static List<MovieDTOResponse> toDTOList(List<MovieEntity> movies) {
        return movies.stream().map(MovieMapper::toDTO).toList();
    }
}
