package com.respawn.hub.forum.models.records.game;

import com.respawn.hub.forum.models.entities.GameForum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record GameRegisterRequest(
        String name,
        String originalName,
        String description,

        LocalDate launch,

        String backgroundImageUrl,

        String websiteUrl
) {

    public GameForum parseToEntity() {
        return GameForum.builder()
                .name(name)
                .originalName(originalName)
                .description(description)
                .launch(launch)
                .backgroundImageUrl(backgroundImageUrl)
                .websiteUrl(websiteUrl)
                .build();
    }

}
