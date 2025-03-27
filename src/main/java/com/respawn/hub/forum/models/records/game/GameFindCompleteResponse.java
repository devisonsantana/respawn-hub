package com.respawn.hub.forum.models.records.game;

import com.respawn.hub.forum.models.entities.GameForum;

import java.time.LocalDate;

public record GameFindCompleteResponse(
        Long id,
        String name,
        String originalName,
        String description,
        LocalDate launch,
        String backgroundImageUrl,
        String websiteUrl
) {

    public static GameFindCompleteResponse parse(GameForum entity) {
        return GameFindCompleteResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .originalName(entity.getOriginalName())
                .description(entity.getDescription())
                .launch(entity.getLaunch())
                .backgroundImageUrl(entity.getBackgroundImageUrl())
                .websiteUrl(entity.getWebsiteUrl())
                .build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String originalName;
        private String description;
        private LocalDate launch;
        private String backgroundImageUrl;
        private String websiteUrl;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder originalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder launch(LocalDate launch) {
            this.launch = launch;
            return this;
        }

        public Builder backgroundImageUrl(String backgroundImageUrl) {
            this.backgroundImageUrl = backgroundImageUrl;
            return this;
        }

        public Builder websiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }

        public GameFindCompleteResponse build() {
            return new GameFindCompleteResponse(
                    id,
                    name,
                    originalName,
                    description,
                    launch,
                    backgroundImageUrl,
                    websiteUrl
            );
        }
    }
}
