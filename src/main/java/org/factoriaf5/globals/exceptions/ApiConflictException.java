package org.factoriaf5.globals.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when the operation violates a business rule: a duplicate resource
 * (e.g. a genre that already exists) or a disallowed deletion (e.g.
 * deleting a genre that still has movies associated with it).
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class ApiConflictException extends ApiException {

    public ApiConflictException(String message) {
        super(message);
    }

}
