package com.respawn.hub.forum.models.records.game;

import com.respawn.hub.forum.models.entities.GameForum;

import java.time.LocalDate;

public record GameRegisterResponse(
        Long id,
        String name,
        String originalName,
        String description,
        LocalDate release,
        String backgroundImageUrl,
        String websiteUrl
) {

    public static GameRegisterResponse parse(GameForum entity) {
        return GameRegisterResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .originalName(entity.getOriginalName())
                .description(entity.getDescription())
                .release(entity.getLaunch())
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
        private LocalDate release;
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

        public Builder release(LocalDate release) {
            this.release = release;
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

        public GameRegisterResponse build() {
            return new GameRegisterResponse(
                    id,
                    name,
                    originalName,
                    description,
                    release,
                    backgroundImageUrl,
                    websiteUrl
            );
        }
    }
}
