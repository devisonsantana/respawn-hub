package com.respawn.hub.forum.models.records.game;

import com.respawn.hub.forum.models.entities.GameForum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record GameRegisterRequest(
        @NotEmpty(message = "O campo nome deve ser preenchido")
        @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 à 100 caracteres")
        String name,

        @Size(min = 3, max = 100, message = "O campo nome original deve ter entre 3 à 100 caracteres")
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
