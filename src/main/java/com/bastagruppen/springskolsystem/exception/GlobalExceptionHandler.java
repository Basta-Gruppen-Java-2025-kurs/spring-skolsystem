package com.bastagruppen.springskolsystem.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.util.Map;

import static com.bastagruppen.springskolsystem.exception.ErrorResponse.*;
import static org.springframework.http.HttpStatus.*;


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

        final Map<String, String> fieldErrors = extractFieldErrors(ex.getBindingResult());
        final String message = "Validation failed for " + fieldErrors.size() + " field" + (fieldErrors.size() > 1 ? "s" : "");
        final String path = extractRequestPath(request);

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ofError(ex.getMessage(), request.getRequestURI(), BAD_REQUEST, clock.instant());
    }

    @ExceptionHandler(AlreadyEnrolledException.class)
    public ResponseEntity<Object> handleAlreadyEnrolledException(AlreadyEnrolledException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ofError(ex.getMessage(), request.getRequestURI(), CONFLICT, clock.instant());
    }

    @ExceptionHandler(CourseCapacityExceededException.class)
    public ResponseEntity<Object> handleCourseCapacityExceededException(CourseCapacityExceededException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ofError(ex.getMessage(), request.getRequestURI(), CONFLICT, clock.instant());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFoundException(CourseNotFoundException ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ofError(ex.getMessage(), request.getRequestURI(), NOT_FOUND, clock.instant());
    }
}