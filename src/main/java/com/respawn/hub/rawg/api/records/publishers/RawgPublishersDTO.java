package com.respawn.hub.rawg.api.records.publishers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgPublishersDTO(
    @JsonAlias("id") Integer id,
    @JsonAlias("name") String name,
    @JsonAlias("slug") String slug
) {

}
