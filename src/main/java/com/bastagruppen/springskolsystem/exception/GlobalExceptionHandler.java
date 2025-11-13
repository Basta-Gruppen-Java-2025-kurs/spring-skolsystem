package com.bastagruppen.springskolsystem.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.util.Map;

import static com.bastagruppen.springskolsystem.exception.ErrorResponse.ofError;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;


@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Clock clock;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        log.error(ex.getMessage(), ex);
        final Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(toMap(
                        FieldError::getField,
                        fieldError -> ofNullable(fieldError.getDefaultMessage()).orElse("Unknown Error"),
                        (existing, replacement) -> replacement
                ));
        final String message = "Validation failed for " + fieldErrors.size() + " field(s)";
        final String path = ((ServletWebRequest) request).getRequest().getRequestURI();

        return ofError(message, path, BAD_REQUEST, fieldErrors, clock.instant());
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ofError(ex.getMessage(), request.getRequestURI(), NOT_FOUND, clock.instant());
    }

    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<Object> handleIllegalOperation(IllegalOperationException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ofError(ex.getMessage(), request.getRequestURI(), CONFLICT, clock.instant());
    }
}