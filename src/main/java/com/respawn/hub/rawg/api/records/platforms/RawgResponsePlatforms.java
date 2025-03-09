package com.respawn.hub.rawg.api.records.platforms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgResponsePlatforms(@JsonAlias("platforms") List<RawgPlatformsListDTO> platforms
) {

}
