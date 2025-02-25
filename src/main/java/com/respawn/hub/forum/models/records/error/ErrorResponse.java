package com.respawn.hub.forum.models.records.error;

import java.time.Instant;

public record ErrorResponse(
        Integer statusCode,
        String status,
        Instant timestamp,
        String errorMessage,
        String path
) {

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

        public ErrorResponse build() {
            return new ErrorResponse(
                    statusCode,
                    status,
                    timestamp,
                    errorMessage,
                    path
            );
        }
    }
}
