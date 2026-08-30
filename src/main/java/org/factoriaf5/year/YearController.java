package org.factoriaf5.year;

import jakarta.validation.Valid;
import org.factoriaf5.year.dtos.YearDTORequest;
import org.factoriaf5.year.dtos.YearDTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Supporting CRUD for Year (needed to be able to create movies).
 */
@RestController
@RequestMapping(path = "${api-endpoint}/years")
public class YearController {

    private final YearServiceImpl service;

    public YearController(YearServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public List<YearDTOResponse> index() {
        return service.getEntities();
    }

    @GetMapping("{id}")
    public YearDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("")
    public ResponseEntity<YearDTOResponse> store(@Valid @RequestBody YearDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.storeEntity(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<YearDTOResponse> update(@PathVariable Long id, @Valid @RequestBody YearDTORequest dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
