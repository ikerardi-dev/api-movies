package org.factoriaf5.globals;

import jakarta.servlet.http.HttpServletRequest;
import org.factoriaf5.globals.exceptions.ApiException;
import org.factoriaf5.globals.exceptions.ApiNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler (@RestControllerAdvice), shown in class.
 * Centralizes translating any exception into a consistent JSON response,
 * instead of letting Spring return a default error page or a generic,
 * unfriendly error body.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ApiNotFoundException ex, HttpServletRequest request) {
        ErrorResponse body = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, HttpServletRequest request) {
        // ApiConflictException (409) carries its own @ResponseStatus; every other
        // ApiException is treated as a generic business error (400).
        HttpStatus status = resolveStatus(ex);
        ErrorResponse body = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse body = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request",
                "Validation error in the submitted data", request.getRequestURI(), details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest request) {
        ErrorResponse body = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    private HttpStatus resolveStatus(ApiException ex) {
        org.springframework.web.bind.annotation.ResponseStatus annotation = ex.getClass()
                .getAnnotation(org.springframework.web.bind.annotation.ResponseStatus.class);
        return annotation != null ? annotation.code() : HttpStatus.BAD_REQUEST;
    }
}
