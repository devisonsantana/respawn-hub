package com.respawn.hub.forum.models.records.error;

public record FieldValidationErrorResponse(
        String fieldName,
        String message
) {
}
