package com.respawn.hub.rawg.api.models;

import java.time.LocalDate;
import java.util.List;

import com.respawn.hub.rawg.api.records.games.RawgGameDetailDTO;

public class Game {
    private Integer id;
    private String title;
    private String originalTitle;
    private String slug;
    private Double rating;
    private Integer ratingTop;
    private String description;
    private String descriptionRaw;
    private LocalDate release;
    private String backgroundImg;
    private String website;
    private List<Genre> genres;
    private List<Tag> tags;
    private List<Platform> platforms;
    private List<Developer> developers;

    public Game(RawgGameDetailDTO gameDetail) {
        id = gameDetail.id();
        title = gameDetail.name();
        originalTitle = gameDetail.originalName();
        slug = gameDetail.slug();
        rating = gameDetail.rating();
        ratingTop = gameDetail.ratingTop();
        description = gameDetail.description();
        descriptionRaw = gameDetail.descriptionRaw();
        release = LocalDate.parse(gameDetail.released());
        backgroundImg = gameDetail.backgroundImage();
        website = gameDetail.website();
        genres = gameDetail.genres().stream().map(Genre::new).toList();
        tags = gameDetail.tags().stream().map(Tag::new).toList();
        platforms = gameDetail.platforms().stream().map(p -> new Platform(p.platform())).toList();
        developers = gameDetail.developers().stream().map(Developer::new).toList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingTop() {
        return ratingTop;
    }

    public void setRatingTop(Integer ratingTop) {
        this.ratingTop = ratingTop;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionRaw() {
        return descriptionRaw;
    }

    public void setDescriptionRaw(String descriptionRaw) {
        this.descriptionRaw = descriptionRaw;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Game other = (Game) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Game\nid=" + id + ",\ntitle=" + title + ",\noriginalTitle=" + originalTitle + ",\nslug=" + slug
                + ",\nrating=" + rating + ",\nratingTop=" + ratingTop + ",\ndescription=" + description
                + ",\ndescriptionRaw=" + descriptionRaw + ",\nrelease=" + release + ",\nbackgroundImg=" + backgroundImg
                + ",\nwebsite=" + website + ",\ngenres=" + genres + ",\ntags=" + tags + ",\nplatforms=" + platforms
                + ",\ndevelopers=" + developers;
    }

}
