package com.respawn.hub.forum.models.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "games")
public class GameForum implements Serializable {

    @Serial private static final long serialVersionUID = 1133021496152260324L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column private String name;
    @Column private String originalName;
    @Column private String description;
    @Column private LocalDate launch;
    @Column private String backgroundImageUrl;
    @Column private String websiteUrl;

    // TODO: incluir os relacionamentos

    @Deprecated
    protected GameForum() {
    }

    public GameForum(String name, String originalName, String description, LocalDate launch,
                     String backgroundImageUrl, String websiteUrl) {
        this.name = name;
        this.originalName = originalName;
        this.description = description;
        this.launch = launch;
        this.backgroundImageUrl = backgroundImageUrl;
        this.websiteUrl = websiteUrl;
    }

    public GameForum(Long id, String name, String originalName, String description, LocalDate launch,
                     String backgroundImageUrl, String websiteUrl) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.description = description;
        this.launch = launch;
        this.backgroundImageUrl = backgroundImageUrl;
        this.websiteUrl = websiteUrl;
    }

    public GameForum(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.description = builder.description;
        this.launch = builder.launch;
        this.backgroundImageUrl = builder.backgroundImageUrl;
        this.websiteUrl = builder.websiteUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLaunch() {
        return launch;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameForum gameForum = (GameForum) o;
        return Objects.equals(name, gameForum.name) &&
                Objects.equals(originalName, gameForum.originalName) &&
                Objects.equals(launch, gameForum.launch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, originalName, launch);
    }

    @Override
    public String toString() {
        return "GameForum { " +
                "id: %d" +
                ", name: '%s'" +
                ", originalName: '%s'" +
                ", description: '%s'" +
                ", launch: '%s'" +
                ", backgroundImageUrl: '%s'" +
                ", websiteUrl: '%s'" +
                "}".formatted(id, name, originalName, description, launch, backgroundImageUrl, websiteUrl);
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

        public GameForum build() {
            return new GameForum(this);
        }
    }
}
