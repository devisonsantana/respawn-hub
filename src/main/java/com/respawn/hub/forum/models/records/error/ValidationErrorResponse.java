package com.respawn.hub.forum.models.records.error;

import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.List;

public record ValidationErrorResponse(
        Integer statusCode,
        String status,
        Instant timestamp,
        String errorMessage,
        String path,
        List<FieldValidationErrorResponse> fieldErrors
) {

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(
                new FieldValidationErrorResponse(
                        fieldError.getField(), fieldError.getDefaultMessage()
                )
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer statusCode;
        private String status;
        private Instant timestamp;
        private String errorMessage;
        private String path;

        public Builder statusCode(Integer statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public ValidationErrorResponse build() {
            return new ValidationErrorResponse(
                    statusCode,
                    status,
                    timestamp,
                    errorMessage,
                    path,
                    List.of()
            );
        }
    }
}
