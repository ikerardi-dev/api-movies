package org.factoriaf5.genre;

import jakarta.validation.Valid;
import org.factoriaf5.genre.dtos.GenreDTORequest;
import org.factoriaf5.genre.dtos.GenreDTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Supporting CRUD for Genre (needed to be able to create movies).
 */
@RestController
@RequestMapping(path = "${api-endpoint}/genres")
public class GenreController {

    private final GenreServiceImpl service;

    public GenreController(GenreServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public List<GenreDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public GenreDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<GenreDTOResponse> store(@Valid @RequestBody GenreDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.storeEntity(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<GenreDTOResponse> update(@PathVariable Long id, @Valid @RequestBody GenreDTORequest dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
