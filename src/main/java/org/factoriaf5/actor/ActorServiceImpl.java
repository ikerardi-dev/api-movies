package org.factoriaf5.actor;

import org.factoriaf5.actor.dtos.ActorDTORequest;
import org.factoriaf5.actor.dtos.ActorDTOResponse;
import org.factoriaf5.actor.mappers.ActorMapper;
import org.factoriaf5.globals.exceptions.ApiNotFoundException;
import org.factoriaf5.implementations.InterfaceGenericEditService;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActorServiceImpl implements InterfaceGenericGetService<ActorDTOResponse>,
        InterfaceGenericEditService<ActorDTORequest, ActorDTOResponse> {

    private final ActorRepository repository;

    public ActorServiceImpl(ActorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActorDTOResponse> getEntities() {
        return repository.findAll().stream().map(ActorMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ActorDTOResponse getById(Long id) {
        return ActorMapper.toDTO(findEntity(id));
    }

    @Override
    public ActorDTOResponse storeEntity(ActorDTORequest dto) {
        ActorEntity actor = ActorMapper.toEntity(dto);
        return ActorMapper.toDTO(repository.save(actor));
    }

    @Override
    public ActorDTOResponse updateEntity(Long id, ActorDTORequest dto) {
        ActorEntity actor = findEntity(id);
        actor.setName(dto.name());
        actor.setNationality(dto.nationality());
        actor.setDateOfBirth(dto.dateOfBirth());
        return ActorMapper.toDTO(repository.save(actor));
    }

    @Override
    public void deleteEntity(Long id) {
        ActorEntity actor = findEntity(id);
        // Unlink from every movie before deleting so no orphan rows are left
        // behind in the movie_actor join table.
        actor.getMovies().forEach(m -> m.getActors().remove(actor));
        repository.delete(actor);
    }

    private ActorEntity findEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> ApiNotFoundException.of("Actor", id));
    }
}
