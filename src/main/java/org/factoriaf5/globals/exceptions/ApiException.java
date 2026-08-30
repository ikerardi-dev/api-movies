package org.factoriaf5.globals.exceptions;

/**
 * Base business exception of the API. Follows the same pattern shown in
 * class (CountryException): an unchecked RuntimeException from which more
 * specific exceptions inherit, caught centrally in the
 * GlobalExceptionHandler.
 */
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
