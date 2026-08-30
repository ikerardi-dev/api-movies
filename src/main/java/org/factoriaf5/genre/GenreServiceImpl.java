package org.factoriaf5.genre;

import org.factoriaf5.genre.dtos.GenreDTORequest;
import org.factoriaf5.genre.dtos.GenreDTOResponse;
import org.factoriaf5.genre.mappers.GenreMapper;
import org.factoriaf5.globals.exceptions.ApiConflictException;
import org.factoriaf5.globals.exceptions.ApiNotFoundException;
import org.factoriaf5.implementations.InterfaceGenericEditService;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements InterfaceGenericGetService<GenreDTOResponse>,
        InterfaceGenericEditService<GenreDTORequest, GenreDTOResponse> {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreDTOResponse> getEntities() {
        return repository.findAll().stream().map(GenreMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDTOResponse getById(Long id) {
        return GenreMapper.toDTO(findEntity(id));
    }

    @Override
    public GenreDTOResponse storeEntity(GenreDTORequest dto) {
        repository.findByNameIgnoreCase(dto.name()).ifPresent(g -> {
            throw new ApiConflictException("A genre with the name '" + dto.name() + "' already exists");
        });
        GenreEntity genre = GenreMapper.toEntity(dto);
        return GenreMapper.toDTO(repository.save(genre));
    }

    @Override
    public GenreDTOResponse updateEntity(Long id, GenreDTORequest dto) {
        GenreEntity genre = findEntity(id);
        repository.findByNameIgnoreCase(dto.name())
                .filter(g -> !g.getId().equals(id))
                .ifPresent(g -> {
                    throw new ApiConflictException("A genre with the name '" + dto.name() + "' already exists");
                });
        genre.setName(dto.name());
        return GenreMapper.toDTO(repository.save(genre));
    }

    @Override
    public void deleteEntity(Long id) {
        GenreEntity genre = findEntity(id);
        repository.delete(genre);
    }

    private GenreEntity findEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> ApiNotFoundException.of("Genre", id));
    }
}
