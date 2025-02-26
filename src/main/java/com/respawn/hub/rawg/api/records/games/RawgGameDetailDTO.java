package com.respawn.hub.rawg.api.records.games;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.respawn.hub.rawg.api.records.developers.RawgDevelopersDTO;
import com.respawn.hub.rawg.api.records.genres.RawgGenresDTO;
import com.respawn.hub.rawg.api.records.platforms.RawgPlatformsListDTO;
import com.respawn.hub.rawg.api.records.tags.RawgTagsDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RawgGameDetailDTO(
        @JsonAlias("id") Integer id,
        @JsonAlias("name") String name,
        @JsonAlias("name_original") String originalName,
        @JsonAlias("slug") String slug,
        @JsonAlias("rating") Double rating,
        @JsonAlias("rating_top") Integer ratingTop,
        @JsonAlias("description") String description,
        @JsonAlias("released") String released,
        @JsonAlias("background_image") String backgroundImage,
        @JsonAlias("website") String website,
        @JsonAlias("genres") List<RawgGenresDTO> genres, 
        @JsonAlias("tags") List<RawgTagsDTO> tags,
        @JsonAlias("platforms") List<RawgPlatformsListDTO> platforms,
        @JsonAlias("developers") List<RawgDevelopersDTO> developers) {

}
