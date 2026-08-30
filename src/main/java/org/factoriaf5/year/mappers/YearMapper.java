package org.factoriaf5.year.mappers;

import org.factoriaf5.year.YearEntity;
import org.factoriaf5.year.dtos.YearDTORequest;
import org.factoriaf5.year.dtos.YearDTOResponse;

public class YearMapper {

    public static YearEntity toEntity(YearDTORequest dto) {
        YearEntity year = new YearEntity();
        year.setValue(dto.value());
        return year;
    }

    public static YearDTOResponse toDTO(YearEntity entity) {
        if (entity == null) return null;
        return new YearDTOResponse(entity.getId(), entity.getValue());
    }
}
