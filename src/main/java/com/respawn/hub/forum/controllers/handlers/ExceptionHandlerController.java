package com.respawn.hub.forum.controllers.handlers;

import com.respawn.hub.forum.models.records.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

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
