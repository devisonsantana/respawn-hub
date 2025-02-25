package com.respawn.hub.rawg.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.respawn.hub.rawg.api.models.Game;
import com.respawn.hub.rawg.api.records.RawgGameDTO;
import com.respawn.hub.rawg.api.records.RawgResponseRawgAPI;

@Service
public class RawgGameService {
    @Value("${rawg.endpoint}${rawg.path}${rawg.apikey}")
    private String url;
    @Autowired
    private RawgAPI rawg;

    public RawgResponseRawgAPI getGames() {
        String json = rawg.getJson(url);
        var data = rawg.toDTO(json, RawgResponseRawgAPI.class);
        return data;
    }

    public List<RawgGameDTO> listGameDto() {
        var data = getGames();
        return data.games().stream().collect(Collectors.toList());
    }

    public List<Game> findGames() {
        var data = getGames()
                .games()
                .stream()
                .map(game -> new Game(game, game.platforms()
                        .stream()
                        .map(p -> p.platform()).toList()))
                .collect(Collectors.toList());
        return data;
        // RETORNO ALTERNATIVO
        // return getGames().games().stream().map(game -> {
        //     var platform = game.platforms().stream().map(p -> p.platform()).toList();
        //     return new Game(game, platform);
        // }).toList();
    }
}
