package com.respawn.hub.rawg.api.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgPlatformDTO(@JsonAlias("name") String name) {

}
