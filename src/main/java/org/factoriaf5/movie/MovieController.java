package org.factoriaf5.movie;

import jakarta.validation.Valid;
import org.factoriaf5.movie.dtos.MovieDTORequest;
import org.factoriaf5.movie.dtos.MovieDTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Movie endpoints.
 *
 *  1. GET    ${api-endpoint}/movies             -> get all movies
 *  2. GET    ${api-endpoint}/movies/{id}        -> get a movie by id
 *  3. POST   ${api-endpoint}/movies             -> add a movie
 *  4. PUT    ${api-endpoint}/movies/{id}        -> update a movie
 *  5. DELETE ${api-endpoint}/movies/{id}        -> delete a movie
 *  6. GET    ${api-endpoint}/movies/search      -> findBy title and/or genre (extra endpoint)
 */
@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    private final MovieServiceImpl service;

    public MovieController(MovieServiceImpl service) {
        this.service = service;
    }

    // 1. Get all movies
    @GetMapping("")
    public List<MovieDTOResponse> index() {
        return service.getEntities();
    }

    // 2. Get a movie by its id
    @GetMapping("{id}")
    public MovieDTOResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 3. Add a movie
    @PostMapping("")
    public ResponseEntity<MovieDTOResponse> store(@Valid @RequestBody MovieDTORequest dto) {
        MovieDTOResponse created = service.storeEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 4. Update a movie's data
    @PutMapping("{id}")
    public ResponseEntity<MovieDTOResponse> update(@PathVariable Long id,
                                                     @Valid @RequestBody MovieDTORequest dto) {
        return ResponseEntity.ok(service.updateEntity(id, dto));
    }

    // 5. Delete a movie
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    // 6. Extra findBy endpoint: search movies by title and/or genre
    // Examples: ${api-endpoint}/movies/search?title=matrix
    //           ${api-endpoint}/movies/search?genre=horror
    //           ${api-endpoint}/movies/search?title=star&genre=sci-fi
    @GetMapping("search")
    public ResponseEntity<List<MovieDTOResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {
        return ResponseEntity.ok(service.search(title, genre));
    }
}
