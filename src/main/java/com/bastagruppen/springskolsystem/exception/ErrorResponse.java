package com.bastagruppen.springskolsystem.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
public class ErrorResponse {

    private final String message;
    private final String path;
    private final int status;
    private final String error;
    @JsonInclude(NON_NULL) private final Map<String, String> errors;
    private final String timestamp;

    private ErrorResponse(String message, String path, HttpStatus status, Map<String, String> errors, Instant timestamp) {
        this.message = message;
        this.path = path;
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.errors = errors;
        this.timestamp = timestamp.toString();
    }

    public static ResponseEntity<Object> ofError(final String message,
                                                 final String path,
                                                 final HttpStatus status,
                                                 final Map<String, String> errors,
                                                 final Instant timestamp) {
        ErrorResponse errorResponse = new ErrorResponse(message, path, status, errors, timestamp);
        return new ResponseEntity<>(errorResponse, status);
    }

    public static ResponseEntity<Object> ofError(final String message,
                                                 final String path,
                                                 final HttpStatus status,
                                                 final Instant timestamp) {
        return ofError(message, path, status, null, timestamp);
    }
}