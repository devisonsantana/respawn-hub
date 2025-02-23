package com.respawn.hub.rawg.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.respawn.hub.rawg.api.records.RawgGameDTO;
import com.respawn.hub.rawg.api.records.RawgResponseRawgAPI;

@Service
public class RawgGameService {
    @Value("${rawg.endpoint}${rawg.apikey}")
    private String endpoint;
    @Autowired
    private RawgAPI rawg;

    public RawgResponseRawgAPI getGames() {
        String json = rawg.getJson(endpoint);
        var data = rawg.toDTO(json, RawgResponseRawgAPI.class);
        return data;
    }
    public List<RawgGameDTO> listGameDto(){
        var data = getGames();
        return data.games().stream().collect(Collectors.toList());
    }
}
