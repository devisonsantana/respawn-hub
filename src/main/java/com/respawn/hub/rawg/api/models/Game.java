package com.respawn.hub.rawg.api.models;

import java.time.LocalDate;
import java.util.List;

import com.respawn.hub.rawg.api.records.RawgGameDTO;
import com.respawn.hub.rawg.api.records.RawgPlatformDTO;

public class Game {
    private Integer id;
    private String title;
    private LocalDate release;
    private String backgroundImg;
    private List<Platform> platforms;

    public Game(RawgGameDTO game, List<RawgPlatformDTO> platforms) {
        id = game.id();
        title = game.name();
        release = LocalDate.parse(game.release());
        backgroundImg = game.backgroundImage();
        this.platforms = platforms.stream().map(Platform::new).toList();
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

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

}
