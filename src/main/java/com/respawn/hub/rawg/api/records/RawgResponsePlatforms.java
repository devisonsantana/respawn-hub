package com.respawn.hub.rawg.api.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgResponsePlatforms(
    @JsonAlias("platform") RawgPlatformDTO platform,
    @JsonAlias("released_at") String released
) {

}
