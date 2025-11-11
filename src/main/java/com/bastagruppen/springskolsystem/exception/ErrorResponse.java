package com.bastagruppen.springskolsystem.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class ErrorResponse {

    String message;
    String path;
    int status;
    String error;
    @JsonInclude(NON_NULL) Map<String, String> errors;
    Instant timestamp;

    private ErrorResponse(String message, String path, HttpStatus status, Map<String, String> errors, Instant timestamp) {
        this.message = message;
        this.path = path;
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.errors = errors;
        this.timestamp = timestamp;
    }

    public static ResponseEntity<Object> ofError(final String message,
                                                 final String path,
                                                 final HttpStatus status,
                                                 final Map<String, String> errors,
                                                 final Instant timestamp) {
        final ErrorResponse body = new ErrorResponse(message, path, status, errors, timestamp);
        return new ResponseEntity<>(body, status);
    }

    public static ResponseEntity<Object> ofError(final String message,
                                                 final String path,
                                                 final HttpStatus status,
                                                 final Instant timestamp) {
        return ofError(message, path, status, null, timestamp);
    }
}