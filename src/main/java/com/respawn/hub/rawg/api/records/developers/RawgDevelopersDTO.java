package com.respawn.hub.rawg.api.records.developers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgDevelopersDTO(
    @JsonAlias("id") Integer id,
    @JsonAlias("name") String name,
    @JsonAlias("slug") String slug
) {

}
