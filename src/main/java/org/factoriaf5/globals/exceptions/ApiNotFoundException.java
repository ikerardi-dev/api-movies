package org.factoriaf5.globals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the requested resource (movie, genre, year or actor) does
 * not exist. Same technique shown in class with CountryExceptionNotFound
 * (@ResponseStatus + RuntimeException), generalized for the four entities
 * in the project instead of duplicating one class per entity.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ApiNotFoundException extends ApiException {

    public ApiNotFoundException(String message) {
        super(message);
    }

    public static ApiNotFoundException of(String entityName, Long id) {
        return new ApiNotFoundException(entityName + " not found with id " + id);
    }

}
