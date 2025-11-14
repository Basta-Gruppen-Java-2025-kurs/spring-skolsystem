package com.bastagruppen.springskolsystem.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;

@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class ErrorResponse {

    String message;
    String path;
    int status;
    String error;
    @JsonInclude(NON_NULL)
    Map<String, String> errors;
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

    static String extractRequestPath(final WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    static Map<String, String> extractFieldErrors(final BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .collect(toMap(
                        FieldError::getField,
                        fieldError -> ofNullable(fieldError.getDefaultMessage()).orElse("Unknown Error"),
                        (existing, _replacement) -> existing
                ));
    }
}