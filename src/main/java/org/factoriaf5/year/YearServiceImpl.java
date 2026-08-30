package org.factoriaf5.year;

import org.factoriaf5.year.dtos.YearDTORequest;
import org.factoriaf5.year.dtos.YearDTOResponse;
import org.factoriaf5.year.mappers.YearMapper;
import org.factoriaf5.globals.exceptions.ApiConflictException;
import org.factoriaf5.globals.exceptions.ApiNotFoundException;
import org.factoriaf5.implementations.InterfaceGenericEditService;
import org.factoriaf5.implementations.InterfaceGenericGetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class YearServiceImpl implements InterfaceGenericGetService<YearDTOResponse>,
        InterfaceGenericEditService<YearDTORequest, YearDTOResponse> {

    private final YearRepository repository;

    public YearServiceImpl(YearRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<YearDTOResponse> getEntities() {
        return repository.findAll().stream().map(YearMapper::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public YearDTOResponse getById(Long id) {
        return YearMapper.toDTO(findEntity(id));
    }

    @Override
    public YearDTOResponse storeEntity(YearDTORequest dto) {
        repository.findByValue(dto.value()).ifPresent(y -> {
            throw new ApiConflictException("Year " + dto.value() + " already exists");
        });
        YearEntity year = YearMapper.toEntity(dto);
        return YearMapper.toDTO(repository.save(year));
    }

    @Override
    public YearDTOResponse updateEntity(Long id, YearDTORequest dto) {
        YearEntity year = findEntity(id);
        repository.findByValue(dto.value())
                .filter(y -> !y.getId().equals(id))
                .ifPresent(y -> {
                    throw new ApiConflictException("Year " + dto.value() + " already exists");
                });
        year.setValue(dto.value());
        return YearMapper.toDTO(repository.save(year));
    }

    @Override
    public void deleteEntity(Long id) {
        YearEntity year = findEntity(id);
        repository.delete(year);
    }

    private YearEntity findEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> ApiNotFoundException.of("Year", id));
    }
}
