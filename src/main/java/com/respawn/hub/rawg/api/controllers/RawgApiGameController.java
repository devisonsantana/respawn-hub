package com.respawn.hub.rawg.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.respawn.hub.rawg.api.records.RawgResponseRawgAPI;
import com.respawn.hub.rawg.api.services.RawgGameService;

@RestController
public class RawgApiGameController {
    @Autowired
    private RawgGameService gameService;

    @GetMapping("/games")
    public RawgResponseRawgAPI getGames() {
        return gameService.getGames();
    }
}
