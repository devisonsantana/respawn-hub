package com.respawn.hub.rawg.api.records;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgGameDTO(
        @JsonAlias("id") Integer id,
        @JsonAlias("slug") String slug,
        @JsonAlias("name") String name,
        @JsonAlias("released") String release,
        @JsonAlias("tba") Boolean tba,
        @JsonAlias("background_image") String backgroundImage,
        @JsonAlias("platforms") List<RawgResponsePlatforms> platforms) {

}
