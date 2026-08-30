package org.factoriaf5.actor.mappers;

import org.factoriaf5.actor.ActorEntity;
import org.factoriaf5.actor.dtos.ActorDTORequest;
import org.factoriaf5.actor.dtos.ActorDTOResponse;

public class ActorMapper {

    public static ActorEntity toEntity(ActorDTORequest dto) {
        return new ActorEntity(dto.name(), dto.nationality(), dto.dateOfBirth());
    }

    public static ActorDTOResponse toDTO(ActorEntity entity) {
        if (entity == null) return null;
        return new ActorDTOResponse(entity.getId(), entity.getName(), entity.getNationality(),
                entity.getDateOfBirth());
    }
}
