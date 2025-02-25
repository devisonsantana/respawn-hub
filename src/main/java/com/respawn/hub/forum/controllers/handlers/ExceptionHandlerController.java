package com.respawn.hub.forum.controllers.handlers;

import com.respawn.hub.forum.exceptions.NotFoundException;
import com.respawn.hub.forum.models.records.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException exception, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, HttpServletRequest request) {
        final var timestamp = Instant.now(Clock.systemUTC());
        final var result = ErrorResponse.builder()
                .statusCode(status.value())
                .status(status.name())
                .timestamp(timestamp)
                .errorMessage(message)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(result);
    }

}
