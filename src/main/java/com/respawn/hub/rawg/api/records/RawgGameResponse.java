package com.respawn.hub.rawg.api.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgGameResponse(
        @JsonAlias("id") Integer id,
        @JsonAlias("slug") String slug,
        @JsonAlias("name") String name,
        @JsonAlias("released") String release,
        @JsonAlias("tba") Boolean tba,
        @JsonAlias("background_image") String backgroundImage) {

}
