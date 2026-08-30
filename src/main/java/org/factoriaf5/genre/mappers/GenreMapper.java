package org.factoriaf5.genre.mappers;

import org.factoriaf5.genre.GenreEntity;
import org.factoriaf5.genre.dtos.GenreDTORequest;
import org.factoriaf5.genre.dtos.GenreDTOResponse;

// Static mapper, no Spring bean (same as the CountryMapper shown in class).
public class GenreMapper {

    public static GenreEntity toEntity(GenreDTORequest dto) {
        GenreEntity genre = new GenreEntity();
        genre.setName(dto.name());
        return genre;
    }

    public static GenreDTOResponse toDTO(GenreEntity entity) {
        if (entity == null) return null;
        return new GenreDTOResponse(entity.getId(), entity.getName());
    }
}
