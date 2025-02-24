package com.respawn.hub.rawg.api.models;

import com.respawn.hub.rawg.api.records.RawgPlatformDTO;

public class Platform {
    private Integer id;
    private String name;

    public Platform(RawgPlatformDTO platform) {
        name = platform.name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
