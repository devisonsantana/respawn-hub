package com.respawn.hub.forum.models.records.error;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public record ValidationErrorResponse(
        Integer statusCode,
        String status,
        Instant timestamp,
        String path,
        Map<String, String> fieldErrors
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
            private Integer statusCode;
            private String status;
            private Instant timestamp;
            private String path;
            private Map<String, String> fieldErrors = new HashMap<>();

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

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder fieldErrors(Map<String, String> fieldErrors) {
            this.fieldErrors.putAll(fieldErrors);
            return this;
        }

        public ValidationErrorResponse build() {
            return new ValidationErrorResponse(
                    statusCode, status, timestamp, path, fieldErrors
            );
        }
    }
}
