package com.respawn.hub.rawg.api.interfaces;

public interface IRawgMethods {
    public String getJson(String endpoint);

    public <T> T toDTO(String json, Class<T> classType);
}
