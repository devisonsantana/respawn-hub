package com.respawn.hub.rawg.api.records.platforms;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgPlatformsListDTO(@JsonAlias("platform") RawgPlatformsDTO platform) {

}
