package com.respawn.hub.forum.models.records.game;

import com.respawn.hub.forum.models.entities.GameForum;

import java.time.LocalDate;

public record GameRegisterRequest(
        String name,
        String originalName,
        String description,
        LocalDate release,
        String backgroundImageUrl,
        String websiteUrl
) {

    public GameForum parseToEntity() {
        return GameForum.builder()
                .name(name)
                .originalName(originalName)
                .description(description)
                .release(release)
                .backgroundImageUrl(backgroundImageUrl)
                .websiteUrl(websiteUrl)
                .build();
    }

}
