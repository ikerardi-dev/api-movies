package org.factoriaf5.actor;

import jakarta.validation.Valid;
import org.factoriaf5.actor.dtos.ActorDTORequest;
import org.factoriaf5.actor.dtos.ActorDTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Supporting CRUD for Actor (needed to be able to create movies).
 */
@RestController
@RequestMapping(path = "${api-endpoint}/actors")
public class ActorController {

    private final ActorServiceImpl service;

    public ActorController(ActorServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public List<ActorDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public ActorDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<ActorDTOResponse> store(@Valid @RequestBody ActorDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.storeEntity(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ActorDTOResponse> update(@PathVariable Long id, @Valid @RequestBody ActorDTORequest dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
